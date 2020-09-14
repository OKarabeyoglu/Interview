package com.trendyol.shoppingcard.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CartDTO;
import com.trendyol.shoppingcard.dto.UserDTO;
import com.trendyol.shoppingcard.entities.User;
import com.trendyol.shoppingcard.intf.CartItemService;
import com.trendyol.shoppingcard.intf.CartService;
import com.trendyol.shoppingcard.intf.CouponService;
import com.trendyol.shoppingcard.intf.UserService;
import com.trendyol.shoppingcard.repositories.CartRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class CartServiceImplTest {
	
	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private UserService userService;
	private CouponService couponService;
	private CartService controller;

	@Before
	public void setup() {
		this.cartRepository = Mockito.mock(CartRepository.class);
		this.cartItemService = Mockito.mock(CartItemService.class);
		this.userService = Mockito.mock(UserService.class);
		this.couponService = Mockito.mock(CouponService.class);
		controller = new CartServiceImpl(cartRepository, cartItemService, userService, couponService);
	}

	@Test
	public void testCompleteOrCancelCart() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(2L);
		userDTO.setName("Ovunc");
		userDTO.setSurname("Karabeyoglu");
		userDTO.setEmail("ovunckarabeyoglu@gmail.com");
		List<CartDTO> cartDTOList = new ArrayList<CartDTO>();
		CartDTO cartDTO = new CartDTO();
		cartDTO.setId(1L);
		userDTO.setCartDTOList(cartDTOList);
		Mockito.when(userService.getSessionUser()).thenReturn(userDTO);
		Mockito.when(cartRepository.findOneById(cartDTO.getId())).thenReturn(Mockito.any());
	}
}
