package com.trendyol.shoppingcard.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.domain.Product;
import com.trendyol.shoppingcard.dto.ProductDTO;
import com.trendyol.shoppingcard.exception.BusinessValidationException;
import com.trendyol.shoppingcard.generator.MockDataGenerator;
import com.trendyol.shoppingcard.intf.ProductService;
import com.trendyol.shoppingcard.repository.CategoryRepository;
import com.trendyol.shoppingcard.repository.ProductRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class ProductServiceImplTest {

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	private ProductService controller;

	@Before
	public void setup() {
		this.productRepository = Mockito.mock(ProductRepository.class);
		this.categoryRepository = Mockito.mock(CategoryRepository.class);
		controller = new ProductServiceImpl(productRepository, categoryRepository);
	}

	@Test
	public void testCreateProduct() {
		ProductDTO productDTO = MockDataGenerator.createProductDTO();
		Product model = Product.toModel(productDTO);
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(model);
		Long id = controller.createProduct(productDTO);
		assertThat(id).isNotNull();
	}

	@Test(expected = BusinessValidationException.class)
	public void testCreateProductTitleIsNull() {
		ProductDTO productDTO = MockDataGenerator.createProductDTO();
		productDTO.setTitle(null);
		Product model = Product.toModel(productDTO);
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(model);
		controller.createProduct(productDTO);
	}

	@Test(expected = BusinessValidationException.class)
	public void testCreateProductAmountIsZero() {
		ProductDTO productDTO = MockDataGenerator.createProductDTO();
		productDTO.setAmount(BigDecimal.ZERO);
		Product model = Product.toModel(productDTO);
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(model);
		controller.createProduct(productDTO);
	}

	@Test(expected = BusinessValidationException.class)
	public void testCreateProductCurrencyIsNull() {
		ProductDTO productDTO = MockDataGenerator.createProductDTO();
		productDTO.setCurrencyCode(null);
		Product model = Product.toModel(productDTO);
		Mockito.when(productRepository.save(Mockito.any())).thenReturn(model);
		controller.createProduct(productDTO);
	}

	@Test(expected = RuntimeException.class)
	public void testCreateProductDTOIsNull() throws Exception {
		ProductDTO productDTO = null;
		controller.createProduct(productDTO);
	}

	@Test
	public void testGetProductsByCategoryId() {
		Mockito.when(categoryRepository.findOneById(1L)).thenReturn(MockDataGenerator.createCategory());
		Mockito.when(categoryRepository.findByParentCategory(MockDataGenerator.createCategory())).thenReturn(null);
		List<ProductDTO> result = controller.getProductsByCategoryId(1L);
		assertThat(result).isNotNull();
	}
}
