package com.trendyol.shoppingcard.intf;

import com.trendyol.shoppingcard.dto.CartDTO;

public interface CartService {

	void completeShopping();

	CartDTO emptyCart();
	
	CartDTO addProductToCart(Long productId, Integer quantity);

	CartDTO removeProductFromCart(Long cartItemId);

	CartDTO updateProductQuantityOnCart(Long cartItemId, Integer quantity);

	CartDTO showCart(Long cartId);

}
