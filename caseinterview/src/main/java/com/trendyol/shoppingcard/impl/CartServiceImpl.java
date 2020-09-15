package com.trendyol.shoppingcard.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trendyol.shoppingcard.dto.CartDTO;
import com.trendyol.shoppingcard.dto.CartItemDTO;
import com.trendyol.shoppingcard.entities.Campaign;
import com.trendyol.shoppingcard.entities.Cart;
import com.trendyol.shoppingcard.entities.CartItem;
import com.trendyol.shoppingcard.entities.Category;
import com.trendyol.shoppingcard.entities.Product;
import com.trendyol.shoppingcard.entities.User;
import com.trendyol.shoppingcard.intf.CartService;
import com.trendyol.shoppingcard.repositories.CartItemRepository;
import com.trendyol.shoppingcard.repositories.CartRepository;
import com.trendyol.shoppingcard.repositories.CouponRepository;
import com.trendyol.shoppingcard.repositories.ProductRepository;
import com.trendyol.shoppingcard.repositories.UserRepository;
import com.trendyol.shoppingcard.util.CartStatus;
import com.trendyol.shoppingcard.util.DiscountType;

@Transactional
@Service
public class CartServiceImpl implements CartService {

	private CartRepository cartRepository;
	private CartItemRepository cartItemRepository;
	private ProductRepository productRepository;
	private UserRepository userRepository;
	private CouponRepository couponRepository;

	@Autowired
	public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository,
			ProductRepository productRepository, UserRepository userRepository, CouponRepository couponRepository) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
		this.couponRepository = couponRepository;
	}

	private User getSessionUser() {
		Long sessionUserId = 2L;
		return userRepository.findOneById(sessionUserId);
	}

	@Override
	public void completeOrCancelShopping() {
		// paid or cart cancelled
		User user = this.getSessionUser();
		Optional<Cart> cart = user.getCartList().stream().filter(c -> CartStatus.ACTIVE.equals(c.getStatus()))
				.findFirst();
		if (cart.isPresent()) {
			Cart model = cartRepository.findOneById(cart.get().getId());
			model.setStatus(CartStatus.PASSIVE);
		}
	}

	private Cart getActiveCart() {
		Cart activeCart = null;
		User user = this.getSessionUser();
		Optional<Cart> cart = user.getCartList().stream().filter(c -> CartStatus.ACTIVE.equals(c.getStatus()))
				.findFirst();
		// if user's active cart exists, use it, else new cart created
		if (cart.isPresent() && cart.get().getId() != null) {
			activeCart = cartRepository.findOneById(cart.get().getId());
		} else {
			Cart model = new Cart();
			model.setCreateDate(LocalDate.now());
			model.setModifiedDate(LocalDate.now());
			model.setStatus(CartStatus.ACTIVE);
			model.setUser(user);
			activeCart = cartRepository.save(model);
		}
		return activeCart;
	}

	private Campaign findApplicableCampaign(Category category) {
		Campaign campaign = null;
		// find campaign recursively
		if (category != null) {
			if (category.getCampaign() != null) {
				campaign = category.getCampaign();
			} else {
				campaign = this.findApplicableCampaign(category.getParentCategory());
			}
		}
		return campaign;
	}

	private BigDecimal calculateCampaignDiscount(CartItem cartItem, BigDecimal cartItemAmount) {
		BigDecimal discountedAmount = cartItemAmount;
		Campaign applicableCampaign = this.findApplicableCampaign(cartItem.getProduct().getCategory());
		if (applicableCampaign != null) {
			// can use the campaign
			if (cartItem.getQuantity() >= applicableCampaign.getMinimumProductCount()) {
				// Discount type can be rate or amount
				if (DiscountType.RATE.equals(applicableCampaign.getDiscountType())) {
					BigDecimal calculatedDiscountAmount = cartItemAmount
							.multiply(applicableCampaign.getDiscountAmount()).divide(new BigDecimal(100))
							.setScale(2, RoundingMode.HALF_UP);
					discountedAmount = cartItemAmount.subtract(calculatedDiscountAmount);
				} else {
					discountedAmount = cartItemAmount.subtract(applicableCampaign.getDiscountAmount());
				}
			}
		}
		return discountedAmount;
	}

	private CartDTO calculateCartAmount(Long cartId) {
		Cart cart = cartRepository.findOneById(cartId);
		CartDTO cartDTO = Cart.toDTO(cart);
		List<CartItem> cartList = cartItemRepository.findAllByCart(cart);
		List<CartItemDTO>  cartItemDTOList = new ArrayList<>();
		for (CartItem cartItem : cartList) {
			CartItemDTO cartItemDTO = CartItem.toDTO(cartItem);
			cartItemDTO.setAmount(cartItem.getProduct().getAmount().multiply(new BigDecimal(cartItem.getQuantity())));
			cartItemDTO.setDiscountedAmount(this.calculateCampaignDiscount(cartItem, cartItemDTO.getAmount()));
			cartItemDTOList.add(cartItemDTO);
			cartDTO.setAmount(cartDTO.getAmount().add(cartItemDTO.getAmount()));
			cartDTO.setDiscountedAmount(cartDTO.getDiscountedAmount().add(cartItemDTO.getDiscountedAmount()));
		}
		cartDTO.setCartItemList(cartItemDTOList);
		return cartDTO;
	}

	@Override
	public CartDTO addProductToCart(Long productId, Integer quantity) {
		Cart userCart = this.getActiveCart();
		CartItem currentCartItem;
		Optional<CartItem> cartItem = userCart.getCartItemList().stream()
				.filter(c -> c.getProduct().getId().equals(productId)).findFirst();
		// if product has already added to cartItem, so product quantity is increased
		// otherwise new cart item created
		if (cartItem.isPresent()) {
			Integer updatedProductQuantity = cartItem.get().getQuantity() + quantity;
			currentCartItem = cartItemRepository.findOneById(cartItem.get().getId());
			currentCartItem.setQuantity(updatedProductQuantity);
		} else {
			CartItem newCartItem = new CartItem();
			newCartItem.setCart(userCart);
			newCartItem.setCreateDate(LocalDate.now());
			newCartItem.setModifiedDate(LocalDate.now());
			newCartItem.setQuantity(quantity);
			Product product = productRepository.findOneById(productId);
			newCartItem.setProduct(product);
			currentCartItem = cartItemRepository.save(newCartItem);
		}
		return this.calculateCartAmount(userCart.getId());
	}

	@Override
	public CartDTO removeProductFromCart(Long cartItemId) {
		CartItem cartItem = cartItemRepository.findOneById(cartItemId);
		Long cartId = cartItem.getCart().getId();
		cartItemRepository.delete(cartItem);
		return this.calculateCartAmount(cartId);
	}

	@Override
	public CartDTO updateProductQuantityOnCart(Long cartItemId, Integer quantity) {
		CartItem cartItem = cartItemRepository.findOneById(cartItemId);
		if (quantity != null) {
			// If the quantity is entered as zero, the product is deleted if it is a
			// different value, product quantity is updated
			if (quantity == 0) {
				this.removeProductFromCart(cartItemId);
			} else {
				cartItem.setQuantity(quantity);
				cartItemRepository.save(cartItem);
			}
		}
		return this.calculateCartAmount(cartItem.getCart().getId());
	}

	@Override
	public String showCart(Long cartId) {
		Cart cart = cartRepository.findOneById(cartId);
		this.calculateCartAmount(cartId);
		return "";
	}

}
