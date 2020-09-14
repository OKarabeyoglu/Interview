package com.trendyol.shoppingcard.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trendyol.shoppingcard.dto.ProductDTO;
import com.trendyol.shoppingcard.entities.Product;
import com.trendyol.shoppingcard.intf.ProductService;
import com.trendyol.shoppingcard.repositories.ProductRepository;
import com.trendyol.shoppingcard.util.ExceptionDefinition;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	private ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Long createProduct(ProductDTO productDTO) throws Exception {
		if (productDTO == null) {
			throw new Exception(ExceptionDefinition.PRODUCT_DTO_CAN_NOT_BE_NULL_ERROR.getExcetionDefinition());
		}
		return productRepository.save(Product.toModel(productDTO)).getId();
	}

}
