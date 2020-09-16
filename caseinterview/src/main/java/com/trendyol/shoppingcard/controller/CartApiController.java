package com.trendyol.shoppingcard.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trendyol.shoppingcard.ApiEndpoints;
import com.trendyol.shoppingcard.intf.CartService;
import com.trendyol.shoppingcard.request.AddProductToChartRequest;
import com.trendyol.shoppingcard.request.RemoveProductFromChartRequest;
import com.trendyol.shoppingcard.request.UpdateProductQuantityOnChartRequest;
import com.trendyol.shoppingcard.response.BaseApiResponse;
import com.trendyol.shoppingcard.response.CartResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = ApiEndpoints.CART_API, produces = { ApiEndpoints.RESPONSE_CONTENTTYPE }, consumes = {
		MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Api(value = "cart-api")
public class CartApiController {

	private CartService cartService;

	@Autowired
	public CartApiController(CartService cartService) {
		this.cartService = cartService;
	}

	@PostMapping(value = "/add/product")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "addProductToCart", notes = "add product to cart")
	public CartResponse addProductToCart(@RequestBody AddProductToChartRequest request) {
		return new CartResponse(cartService.addProductToCart(request.getProductId(), request.getQuantity()));
	}

	@PostMapping(value = "/remove/product")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "removeProductFromCart", notes = "remove product from cart")
	public CartResponse removeProductFromCart(@RequestBody RemoveProductFromChartRequest request) {
		return new CartResponse(cartService.removeProductFromCart(request.getCartItemId()));
	}

	@PostMapping(value = "/update/product/quantity")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "updateProductQuantityOnCart", notes = "update product quantity on cart")
	public CartResponse updateProductQuantityOnCart(@RequestBody UpdateProductQuantityOnChartRequest request) {
		return new CartResponse(
				cartService.updateProductQuantityOnCart(request.getCartItemId(), request.getQuantity()));
	}

	@GetMapping(value = "/show/{CART_ID}", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "showCart", notes = "show cart")
	public CartResponse showCart(@NotNull @Positive @PathVariable("CART_ID") Long cartId) {
		return new CartResponse(cartService.showCart(cartId));
	}

	@PostMapping(value = "/empty", consumes = { MediaType.ALL_VALUE })
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "empty", notes = "empty cart")
	public CartResponse emptyCart() {
		return new CartResponse(cartService.emptyCart());
	}

	@PostMapping(value = "/complete")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "complete", notes = "complete shopping")
	public BaseApiResponse completeShopping() {
		cartService.completeShopping();
		return new BaseApiResponse(true);
	}

}
