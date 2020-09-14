package com.trendyol.shoppingcard.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trendyol.shoppingcard.dto.CartDTO;
import com.trendyol.shoppingcard.dto.UserDTO;
import com.trendyol.shoppingcard.entities.Cart;
import com.trendyol.shoppingcard.intf.CartItemService;
import com.trendyol.shoppingcard.intf.CartService;
import com.trendyol.shoppingcard.intf.CouponService;
import com.trendyol.shoppingcard.intf.UserService;
import com.trendyol.shoppingcard.repositories.CartRepository;
import com.trendyol.shoppingcard.util.CartStatus;

@Transactional
@Service
public class CartServiceImpl implements CartService {
	
	private CartRepository cartRepository;
	private CartItemService cartItemService;
	private UserService userService;
	private CouponService couponService;
	
	@Autowired
	public CartServiceImpl(CartRepository cartRepository, CartItemService cartItemService, UserService userService, CouponService couponService) {
		this.cartRepository = cartRepository;
		this.cartItemService = cartItemService;
		this.userService = userService;
		this.couponService = couponService;
	}
	
	@Override
	public void completeOrCancelCart() {
		UserDTO userDTO = userService.getSessionUser();
		Optional<CartDTO> cartDTO = userDTO.getCartDTOList().stream().filter(c -> CartStatus.ACTIVE.equals(c.getStatus())).findFirst();
		Cart model = cartRepository.findOneById(cartDTO.get().getId());
		model.setStatus(CartStatus.PASSIVE);
	}

}
