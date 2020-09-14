package com.trendyol.shoppingcard.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CategoryDTO;
import com.trendyol.shoppingcard.dto.ProductDTO;
import com.trendyol.shoppingcard.entities.Product;
import com.trendyol.shoppingcard.intf.ProductService;
import com.trendyol.shoppingcard.repositories.ProductRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class ProductServiceImplTest {

	private ProductRepository productRepository;

	private ProductService controller;

	@Before
	public void setup() {
		this.productRepository = Mockito.mock(ProductRepository.class);
		controller = new ProductServiceImpl(productRepository);
	}

	@Test
	public void testCreateProduct() throws Exception {
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
		Product model = Product.toModel(productDTO);
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(model);
		Long id = controller.createProduct(productDTO);
		assertThat(id).isNotNull();
	}

	@Test(expected = Exception.class)
	public void testCreateProductDTOIsNull() throws Exception {
		ProductDTO productDTO = null;
		controller.createProduct(productDTO);
	}
}
