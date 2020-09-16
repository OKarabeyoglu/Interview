package com.trendyol.shoppingcard;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.controller.ProductApiController;
import com.trendyol.shoppingcard.dto.ProductDTO;
import com.trendyol.shoppingcard.generator.MockDataGenerator;
import com.trendyol.shoppingcard.intf.ProductService;
import com.trendyol.shoppingcard.request.ProductDTORequest;
import com.trendyol.shoppingcard.response.ProductListResponse;
import com.trendyol.shoppingcard.response.SaveResponse;

@RunWith(BlockJUnit4ClassRunner.class)
public class ProductApiControllerTest {

	private ProductService productService;

	private ProductApiController controller;

	@Before
	public void setup() {
		this.productService = Mockito.mock(ProductService.class);
		controller = new ProductApiController(productService);
	}

	@Test
	public void testSaveProduct() {
		ProductDTO productDTO = MockDataGenerator.createProductDTO();
		Mockito.when(productService.createProduct(productDTO)).thenReturn(1L);
		ProductDTORequest request = new ProductDTORequest();
		request.setProductDTO(productDTO);
		SaveResponse response = controller.saveProduct(request);
		assertThat(response).isNotNull();
	}
	
	@Test
	public void testGetProductsByCategoryId() {
		List<ProductDTO> productDTOList = new ArrayList<>(); 
		productDTOList.add(MockDataGenerator.createProductDTO());
		Mockito.when(productService.getProductsByCategoryId(1L)).thenReturn(productDTOList);
		ProductListResponse response = new ProductListResponse();
		response.setProductDTOList(null);
		response.getProductDTOList();
		response = controller.getProductsByCategoryId(1L);
		assertThat(response).isNotNull();
	}

}
