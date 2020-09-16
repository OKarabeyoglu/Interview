package com.trendyol.shoppingcard.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trendyol.shoppingcard.dto.ProductDTO;
import com.trendyol.shoppingcard.entities.Category;
import com.trendyol.shoppingcard.entities.Product;
import com.trendyol.shoppingcard.intf.ProductService;
import com.trendyol.shoppingcard.repositories.CategoryRepository;
import com.trendyol.shoppingcard.repositories.ProductRepository;

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

	@Override
	public Long createProduct(ProductDTO productDTO)  {
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
