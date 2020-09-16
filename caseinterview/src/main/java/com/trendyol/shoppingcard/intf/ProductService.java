package com.trendyol.shoppingcard.intf;

import java.util.List;

import com.trendyol.shoppingcard.dto.ProductDTO;

public interface ProductService {

	public Long createProduct(ProductDTO productDTO);

	List<ProductDTO> getProductsByCategoryId(Long categoryId);

}
