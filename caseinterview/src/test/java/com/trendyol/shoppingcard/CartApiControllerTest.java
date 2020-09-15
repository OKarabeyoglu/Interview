package com.trendyol.shoppingcard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.generator.MockDataGenerator;
import com.trendyol.shoppingcard.intf.CartService;
import com.trendyol.shoppingcard.request.AddProductToChartRequest;
import com.trendyol.shoppingcard.request.RemoveProductFromChartRequest;
import com.trendyol.shoppingcard.request.ShowCartRequest;
import com.trendyol.shoppingcard.request.UpdateProductQuantityOnChartRequest;
import com.trendyol.shoppingcard.response.BaseApiResponse;
import com.trendyol.shoppingcard.response.CartResponse;
import com.trendyol.shoppingcard.response.ShowCartResponse;

@RunWith(BlockJUnit4ClassRunner.class)
public class CartApiControllerTest {

	private CartService cartService;

	private CartApiController controller;

	@Before
	public void setup() {
		this.cartService = Mockito.mock(CartService.class);
		controller = new CartApiController(cartService);
	}
	
	@Test
	public void testAddProductToCart() {
		AddProductToChartRequest request = new AddProductToChartRequest();
		request.setProductId(1L);
		request.setQuantity(2);
		Mockito.when(cartService.addProductToCart(request.getProductId(), request.getQuantity())).thenReturn(MockDataGenerator.createCartDTO());
		CartResponse response = controller.addProductToCart(request);
		assertThat(response).isNotNull();
	}
	
	@Test
	public void testRemoveProductFromCart() {
		RemoveProductFromChartRequest request = new RemoveProductFromChartRequest();
		request.setCartItemId(1L);
		Mockito.when(cartService.removeProductFromCart(request.getCartItemId())).thenReturn(MockDataGenerator.createCartDTO());
		CartResponse response = controller.removeProductFromCart(request);
		assertThat(response).isNotNull();
	}
	
	@Test
	public void testUpdateProductQuantityOnCart() {
		UpdateProductQuantityOnChartRequest request = new UpdateProductQuantityOnChartRequest();
		request.setCartItemId(1L);
		request.setQuantity(3);
		Mockito.when(cartService.updateProductQuantityOnCart(request.getCartItemId(), request.getQuantity())).thenReturn(MockDataGenerator.createCartDTO());
		CartResponse response = new CartResponse();
		response.setCartDTO(null);
		response = controller.updateProductQuantityOnCart(request);
		assertThat(response).isNotNull();
	}
	
	@Test
	public void testShowCart() {
		ShowCartRequest request = new ShowCartRequest();
		request.setCartId(1L);
		Mockito.when(cartService.showCart(request.getCartId())).thenReturn("Cart");
		ShowCartResponse response = controller.showCart(request);
		assertThat(response).isNotNull();
	}
	
	@Test
	public void testCompleteOrCancelShopping() {
		BaseApiResponse response = controller.completeOrCancelShopping();
		assertThat(response).isNotNull();
	}
}
