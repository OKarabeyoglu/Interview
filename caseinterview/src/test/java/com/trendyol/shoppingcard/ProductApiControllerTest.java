package com.trendyol.shoppingcard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CategoryDTO;
import com.trendyol.shoppingcard.dto.ProductDTO;
import com.trendyol.shoppingcard.intf.ProductService;
import com.trendyol.shoppingcard.request.ProductDTORequest;
import com.trendyol.shoppingcard.response.SaveResponse;

public class ProductApiControllerTest {

	private ProductService productService;

	private ProductApiController controller;

	@Before
	public void setup() {
		this.productService = Mockito.mock(ProductService.class);
		controller = new ProductApiController(productService);
	}

	@Test
	public void testSaveProduct() throws Exception {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setAmount(new BigDecimal(100));
		productDTO.setTitle("T-Shirt");
		productDTO.setCurrencyCode("TL");
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setTitle("Clothes");
		categoryDTO.setParentCategory(null);
		categoryDTO.setCampaignDTOList(null);
		productDTO.setCategory(categoryDTO);
		productDTO.setId(1L);
		Mockito.when(productService.createProduct(productDTO)).thenReturn(1L);
		ProductDTORequest request = new ProductDTORequest();
		request.setProductDTO(productDTO);
		SaveResponse response = controller.saveProduct(request);
		assertThat(response, is(notNullValue()));
	}

}
