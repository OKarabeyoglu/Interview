package com.trendyol.shoppingcard.intf;

import java.util.List;

import com.trendyol.shoppingcard.dto.CategoryDTO;

public interface CategoryService {
	public Long createCategory(CategoryDTO categoryDTO);

	List<CategoryDTO> getCategoriesByParentCategory(Long parentCategoryId);

}
