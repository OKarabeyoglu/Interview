package com.trendyol.shoppingcard.impl;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CartDTO;
import com.trendyol.shoppingcard.dto.UserDTO;
import com.trendyol.shoppingcard.entities.Cart;
import com.trendyol.shoppingcard.entities.User;
import com.trendyol.shoppingcard.intf.UserService;
import com.trendyol.shoppingcard.repositories.UserRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class UserServiceImplTest {

	private UserRepository userRepository;

	private UserService controller;

	@Before
	public void setup() {
		this.userRepository = Mockito.mock(UserRepository.class);
		controller = new UserServiceImpl(userRepository);
	}
	
	@Test
	public void testGetSessionUser() {
		User model = new User();
		model.setId(2L);
		model.setName("Ovunc");
		model.setSurname("Karabeyoglu");
		model.setEmail("ovunckarabeyoglu@gmail.com");
		List<Cart> cartList = new ArrayList<Cart>();
		Cart cart = new Cart();
		cart.setId(1L);
		cartList.add(cart);
		model.setCartList(cartList);
		Mockito.when(userRepository.findOneById(2L)).thenReturn(model);
		UserDTO response = controller.getSessionUser();
		assertThat(response).isNotNull();
	}
	
	@Test
	public void testGetSessionUserSessionUserNotFound() {
		Mockito.when(userRepository.findOneById(1L)).thenReturn(null);
		UserDTO response = controller.getSessionUser();
		assertThat(response).isNotNull();
	}

}
