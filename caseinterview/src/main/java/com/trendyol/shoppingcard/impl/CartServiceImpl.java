package com.trendyol.shoppingcard.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.trendyol.shoppingcard.dto.CartDTO;
import com.trendyol.shoppingcard.dto.CartItemDTO;
import com.trendyol.shoppingcard.dto.CouponDiscountDTO;
import com.trendyol.shoppingcard.entities.Campaign;
import com.trendyol.shoppingcard.entities.Cart;
import com.trendyol.shoppingcard.entities.CartItem;
import com.trendyol.shoppingcard.entities.Category;
import com.trendyol.shoppingcard.entities.Coupon;
import com.trendyol.shoppingcard.entities.Product;
import com.trendyol.shoppingcard.entities.User;
import com.trendyol.shoppingcard.intf.CartService;
import com.trendyol.shoppingcard.intf.DeliveryCostService;
import com.trendyol.shoppingcard.repositories.CartItemRepository;
import com.trendyol.shoppingcard.repositories.CartRepository;
import com.trendyol.shoppingcard.repositories.CouponRepository;
import com.trendyol.shoppingcard.repositories.ProductRepository;
import com.trendyol.shoppingcard.repositories.UserRepository;
import com.trendyol.shoppingcard.util.CartStatus;
import com.trendyol.shoppingcard.util.DiscountType;

@Transactional
@Service
public class CartServiceImpl implements CartService, DeliveryCostService {

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
	public void completeShopping() {
		// paid and complete cart
		User user = this.getSessionUser();
		Cart cart = user.getCartList().stream().filter(c -> CartStatus.ACTIVE.equals(c.getStatus()))
				.findFirst().orElse(null);
		if (cart != null && cart.getId() != null) {
			Cart model = cartRepository.findOneById(cart.getId());
			model.setStatus(CartStatus.PASSIVE);
		}
	}

	@Override
	public CartDTO emptyCart() {
		User user = this.getSessionUser();
		Cart cart = user.getCartList().stream().filter(c -> CartStatus.ACTIVE.equals(c.getStatus()))
				.findFirst().orElse(null);
		CartDTO cartDTO = new CartDTO();
		if (cart != null && cart.getId() != null) {
			Cart model = cartRepository.findOneById(cart.getId());
			model.getCartItemList().removeAll(model.getCartItemList());
			model.setCoupon(null);
			cartItemRepository.deleteAll(model.getCartItemList());
			cartDTO = this.calculateCartAmount(model.getId());
		}
		return cartDTO;
	}

	private Cart getActiveCart() {
		Cart activeCart = null;
		User user = this.getSessionUser();
		Cart cart = user.getCartList().stream().filter(c -> CartStatus.ACTIVE.equals(c.getStatus()))
				.findFirst().orElse(null);
		// if user's active cart exists, use it, else new cart created
		if (cart != null && cart.getId() != null) {
			activeCart = cartRepository.findOneById(cart.getId());
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

	private List<CouponDiscountDTO> findApplicableCoupons(BigDecimal cartAmount) {
		List<Coupon> couponList = couponRepository.findByMinimumCartAmountLessThanEqual(cartAmount);
		List<CouponDiscountDTO> couponDiscountDTOList = new ArrayList<>();
		// find available coupons and to list coupon id and discount amount
		if (!CollectionUtils.isEmpty(couponList)) {
			for (Coupon coupon : couponList) {
				CouponDiscountDTO couponDiscountDTO = new CouponDiscountDTO();
				couponDiscountDTO.setCouponId(coupon.getId());
				couponDiscountDTO.setDiscountAmount(coupon.getDiscountAmount());
				if (DiscountType.RATE.equals(coupon.getDiscountType())) {
					couponDiscountDTO.setDiscountAmount(cartAmount.multiply(coupon.getDiscountAmount())
							.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP));
				}
				couponDiscountDTOList.add(couponDiscountDTO);
			}
		}
		return couponDiscountDTOList;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private CouponDiscountDTO findProfitableCoupon(BigDecimal cartAmount) {
		CouponDiscountDTO profitableCoupon = null;
		List<CouponDiscountDTO> couponDiscountDTOList = this.findApplicableCoupons(cartAmount);
		if (!CollectionUtils.isEmpty(couponDiscountDTOList)) {
			// sorts by the most profitable
			Collections.sort(couponDiscountDTOList, new Comparator() {
				@Override
				public int compare(Object first, Object second) {
					BigDecimal firstAmount = ((CouponDiscountDTO) first).getDiscountAmount();
					BigDecimal secondAmount = ((CouponDiscountDTO) second).getDiscountAmount();
					return secondAmount.compareTo(firstAmount);
				}
			});
			profitableCoupon = couponDiscountDTOList.stream().findFirst().orElse(null);
		}
		return profitableCoupon;
	}

	private void useCoupon(CartDTO cartDTO) {
		CouponDiscountDTO couponDTO = this.findProfitableCoupon(cartDTO.getDiscountedAmount());
		if (couponDTO != null) {
			Cart cart = cartRepository.findOneById(cartDTO.getId());
			cartDTO.setDiscountCouponAmount(couponDTO.getDiscountAmount());
			Coupon coupon = couponRepository.findOneById(couponDTO.getCouponId());
			cart.setCoupon(coupon);
		}
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
							.multiply(applicableCampaign.getDiscountAmount())
							.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
					discountedAmount = cartItemAmount.subtract(calculatedDiscountAmount);
				} else {
					discountedAmount = cartItemAmount.subtract(applicableCampaign.getDiscountAmount());
				}
			}
		}
		return discountedAmount;
	}

	@Override
	public CartDTO addProductToCart(Long productId, Integer quantity) {
		Cart userCart = this.getActiveCart();
		CartItem currentCartItem;
		CartItem cartItem = userCart.getCartItemList().stream()
				.filter(c -> c.getProduct().getId().equals(productId)).findFirst().orElse(null);
		// if product has already added to cartItem, so product quantity is increased
		// otherwise new cart item created
		if (cartItem != null && cartItem.getId() != null) {
			Integer updatedProductQuantity = cartItem.getQuantity() + quantity;
			currentCartItem = cartItemRepository.findOneById(cartItem.getId());
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
		// if one product in the cart, coupon relation is broken
		if(cartItem.getCart().getCartItemList().size() == 1) {
			cartItem.getCart().setCoupon(null);
		}
		cartItem.getCart().getCartItemList().remove(cartItem);
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
	
	private CartDTO calculateCartAmount(Long cartId) {
		Cart cart = cartRepository.findOneById(cartId);
		CartDTO cartDTO = Cart.toDTO(cart);
		List<CartItem> cartList = cartItemRepository.findAllByCart(cart);
		List<CartItemDTO> cartItemDTOList = new ArrayList<>();
		// amounts calculated dynamically as product prices may change while the product
		// is in the cart
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

	/*
		------ SHOPPING CART ------
		Product: Basic T-shirt  Qty: 8  Price: 400.00 TL  Discounted Price: 300.00 TL
		Product: Skinny Jeans   Qty: 3  Price: 300.00 TL  Discounted Price: 270.00 TL
		Product: Blazer Jacket  Qty: 1  Price: 150.00 TL
		Coupon Discount Amount: -144.00 TL
		Delivery Cost: 0 TL
		------ CART SUMMARY ------
		Total Price: 576.00 TL
	 */
	@Override
	public CartDTO showCart(Long cartId) {
		CartDTO cartDTO = this.calculateCartAmount(cartId);
		StringBuilder sb = new StringBuilder();
		this.useCoupon(cartDTO);
		sb.append("------ SHOPPING CART ------").append(System.getProperty("line.separator"));
		if (!CollectionUtils.isEmpty(cartDTO.getCartItemList())) {
			for (CartItemDTO cartItem : cartDTO.getCartItemList()) {
				sb.append("Product: ").append(cartItem.getProduct().getTitle()).append("  Qty: ")
						.append(cartItem.getQuantity()).append("  Price: ").append(cartItem.getAmount()).append(" TL");
				// if amount is not equal to discountedAmount, campaign is used
				if (cartItem.getAmount().compareTo(cartItem.getDiscountedAmount()) != 0) {
					sb.append("  Discounted Price: ").append(cartItem.getDiscountedAmount()).append(" TL")
							.append(System.getProperty("line.separator"));
				} else {
					sb.append(System.getProperty("line.separator"));
				}
			}
			if (cartDTO.getDiscountCouponAmount() != null
					&& cartDTO.getDiscountCouponAmount().compareTo(BigDecimal.ZERO) > 0) {
				sb.append("Coupon Discount Amount: ").append("-").append(cartDTO.getDiscountCouponAmount())
						.append(" TL").append(System.getProperty("line.separator"));
			}
			BigDecimal deliveryCost = this.calculateDeliveryCost(cartDTO.getDiscountedAmount(), cartDTO.getCartItemList().size());
			sb.append("Delivery Cost: ").append(deliveryCost).append(" TL").append(System.getProperty("line.separator"));
			sb.append("------ CART SUMMARY ------").append(System.getProperty("line.separator"));
			sb.append("Total Price: ").append(cartDTO.getDiscountedAmount().subtract(cartDTO.getDiscountCouponAmount()).add(deliveryCost))
					.append(" TL");
		} else {
			sb.append(" - ");
		}
		System.out.println(sb.toString());
		return cartDTO;
	}

}
