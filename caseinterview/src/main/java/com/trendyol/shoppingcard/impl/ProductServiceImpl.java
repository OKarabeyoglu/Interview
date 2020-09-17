package com.trendyol.shoppingcard.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.trendyol.shoppingcard.domain.Category;
import com.trendyol.shoppingcard.domain.Product;
import com.trendyol.shoppingcard.dto.ProductDTO;
import com.trendyol.shoppingcard.exception.BusinessValidationException;
import com.trendyol.shoppingcard.intf.ProductService;
import com.trendyol.shoppingcard.repository.CategoryRepository;
import com.trendyol.shoppingcard.repository.ProductRepository;
import com.trendyol.shoppingcard.util.ShoppingCartBusinessValidationRule;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;
	private CategoryRepository categoryRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository;
	}
	
	private void validateCreate(ProductDTO productDTO) {
		if(productDTO.getAmount() == null || productDTO.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
			throw new BusinessValidationException(ShoppingCartBusinessValidationRule.PRODUCT_AMOUNT_REQUIRED_ERROR);
		}
		if(StringUtils.isEmpty(productDTO.getTitle())) {
			throw new BusinessValidationException(ShoppingCartBusinessValidationRule.PRODUCT_TITLE_REQUIRED_ERROR);
		}
		if(StringUtils.isEmpty(productDTO.getCurrencyCode())) {
			throw new BusinessValidationException(ShoppingCartBusinessValidationRule.PRODUCT_CURRENCY_REQUIRED_ERROR);
		}
	}

	@Override
	public Long createProduct(ProductDTO productDTO)  {
		this.validateCreate(productDTO);
		return productRepository.save(Product.toModel(productDTO)).getId();
	}
	
	@Override
	public List<ProductDTO> getProductsByCategoryId(Long categoryId) {
		// if category is parent, get subcategories and find products related subcategories
		List<Category> categoryList = new ArrayList<>();
		Category category = categoryRepository.findOneById(categoryId);
		if(category.getParentCategory() == null) {
			categoryList = categoryRepository.findByParentCategory(category);
		}
		categoryList.add(category);
		List<Product> productList = productRepository.findAllByCategoryIn(categoryList);
		return Product.toDTOList(productList);
	}
	

}
