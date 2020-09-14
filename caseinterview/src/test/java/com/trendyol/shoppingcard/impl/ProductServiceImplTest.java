package com.trendyol.shoppingcard.impl;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CategoryDTO;
import com.trendyol.shoppingcard.dto.ProductDTO;
import com.trendyol.shoppingcard.entities.Product;
import com.trendyol.shoppingcard.intf.ProductService;
import com.trendyol.shoppingcard.repositories.ProductRepository;

public class ProductServiceImplTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductService controller;

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
		Mockito.when(productRepository.save(model)).thenReturn(model);
		Long id = controller.createProduct(productDTO);
	    assertEquals(1L, id);
	}
	
	@Test(expected = Exception.class)
	public void testCreateProductDTOIsNull() throws Exception {
		ProductDTO productDTO = null;
		controller.createProduct(productDTO);
	}
}
