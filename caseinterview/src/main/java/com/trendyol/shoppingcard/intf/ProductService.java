package com.trendyol.shoppingcard.intf;

import com.trendyol.shoppingcard.dto.ProductDTO;

public interface ProductService {

	public Long createProduct(ProductDTO productDTO) throws Exception;

}
