package com.trendyol.shoppingcard.intf;

import com.trendyol.shoppingcard.dto.CartDTO;

public interface CartService {

	void completeOrCancelShopping();

	CartDTO addProductToCart(Long productId, Integer quantity);

	CartDTO removeProductFromCart(Long cartItemId);

	CartDTO updateProductQuantityOnCart(Long cartItemId, Integer quantity);

	String showCart(Long cartId);

}
