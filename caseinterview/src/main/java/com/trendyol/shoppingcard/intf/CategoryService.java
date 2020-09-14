package com.trendyol.shoppingcard.intf;

import com.trendyol.shoppingcard.dto.CategoryDTO;

public interface CategoryService {
	public Long createCategory(CategoryDTO categoryDTO) throws Exception;

}
