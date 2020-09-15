package com.trendyol.shoppingcard.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CartDTO;
import com.trendyol.shoppingcard.entities.Cart;
import com.trendyol.shoppingcard.entities.CartItem;
import com.trendyol.shoppingcard.entities.User;
import com.trendyol.shoppingcard.generator.MockDataGenerator;
import com.trendyol.shoppingcard.intf.CartService;
import com.trendyol.shoppingcard.repositories.CartItemRepository;
import com.trendyol.shoppingcard.repositories.CartRepository;
import com.trendyol.shoppingcard.repositories.CouponRepository;
import com.trendyol.shoppingcard.repositories.ProductRepository;
import com.trendyol.shoppingcard.repositories.UserRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class CartServiceImplTest {
	
	private CartRepository cartRepository;
	private CartItemRepository cartItemRepository;
	private ProductRepository productRepository;
	private UserRepository userRepository;
	private CouponRepository couponRepository;
	private CartService controller;

	@Before
	public void setup() {
		this.cartRepository = Mockito.mock(CartRepository.class);
		this.cartItemRepository = Mockito.mock(CartItemRepository.class);
		this.productRepository = Mockito.mock(ProductRepository.class);
		this.userRepository = Mockito.mock(UserRepository.class);
		this.couponRepository = Mockito.mock(CouponRepository.class);
		controller = new CartServiceImpl(cartRepository, cartItemRepository, productRepository, userRepository, couponRepository);
	}

	@Test
	public void testCompleteShopping() {
		User user = MockDataGenerator.createUserModel();
		Mockito.when(userRepository.findOneById(2L)).thenReturn(user);
		Mockito.when(cartRepository.findOneById(user.getCartList().get(0).getId())).thenReturn(new Cart());
		controller.completeShopping();
	}
	
	@Test
	public void testEmptyCart() {
		User user = MockDataGenerator.createUserModel();
		Mockito.when(userRepository.findOneById(2L)).thenReturn(user);
		Mockito.when(cartRepository.findOneById(user.getCartList().get(0).getId())).thenReturn(new Cart());
		controller.emptyCart();
	}
	
	@Test
	public void testAddProductToCart() {
		User user = MockDataGenerator.createUserModel();
		Mockito.when(userRepository.findOneById(2L)).thenReturn(user);
		Cart cart = MockDataGenerator.createDefaultCart();
		Cart addedCart = MockDataGenerator.createAddedCart();
		Mockito.when(cartRepository.findOneById(cart.getId())).thenReturn(addedCart);
		Mockito.when(cartRepository.save(cart)).thenReturn(addedCart);
		List<CartItem> cartItemList = MockDataGenerator.createCartItemList();
		Mockito.when(cartItemRepository.findOneById(cartItemList.get(0).getId())).thenReturn(cartItemList.get(0));
		Mockito.when(productRepository.findOneById(cartItemList.get(0).getProduct().getId())).thenReturn(cartItemList.get(0).getProduct());
		Mockito.when(cartItemRepository.save(cartItemList.get(0))).thenReturn(cartItemList.get(0));
	    Mockito.when(cartRepository.findOneById(cartItemList.get(0).getCart().getId())).thenReturn(addedCart);
	    Mockito.when(cartItemRepository.findAllByCart(cartItemList.get(0).getCart())).thenReturn(cartItemList);
		CartDTO result = controller.addProductToCart(cartItemList.get(0).getProduct().getId(), 2);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testUpdateProductQuantityOnCart() {
		List<CartItem> cartItemList = MockDataGenerator.createCartItemList();
		Mockito.when(cartItemRepository.findOneById(cartItemList.get(0).getId())).thenReturn(cartItemList.get(0));
		Mockito.when(cartItemRepository.save(cartItemList.get(0))).thenReturn(cartItemList.get(0));
		Mockito.when(cartRepository.findOneById(cartItemList.get(0).getCart().getId())).thenReturn(cartItemList.get(0).getCart());
		Mockito.when(cartItemRepository.findAllByCart(cartItemList.get(0).getCart())).thenReturn(cartItemList);
		CartDTO result = controller.updateProductQuantityOnCart(cartItemList.get(0).getId(), 2);
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testRemoveProductFromCart() {
		List<CartItem> cartItemList = MockDataGenerator.createCartItemList();
		Mockito.when(cartItemRepository.findOneById(cartItemList.get(0).getId())).thenReturn(cartItemList.get(0));
		Mockito.when(cartRepository.findOneById(cartItemList.get(0).getCart().getId())).thenReturn(cartItemList.get(0).getCart());
		Mockito.when(cartItemRepository.findAllByCart(cartItemList.get(0).getCart())).thenReturn(cartItemList);
		CartDTO result = controller.removeProductFromCart(cartItemList.get(0).getId());
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testShowCart() {
		List<CartItem> cartItemList = MockDataGenerator.createCartItemList();
		Mockito.when(cartRepository.findOneById(cartItemList.get(0).getCart().getId())).thenReturn(cartItemList.get(0).getCart());
		Mockito.when(cartRepository.findOneById(cartItemList.get(0).getCart().getId())).thenReturn(cartItemList.get(0).getCart());
		Mockito.when(cartItemRepository.findAllByCart(cartItemList.get(0).getCart())).thenReturn(cartItemList);
		String result = controller.showCart(cartItemList.get(0).getCart().getId());
		assertThat(result).isNotNull();
	}
}
