package com.trendyol.shoppingcard.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trendyol.shoppingcard.dto.CategoryDTO;
import com.trendyol.shoppingcard.entities.Category;
import com.trendyol.shoppingcard.intf.CategoryService;
import com.trendyol.shoppingcard.repositories.CategoryRepository;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Long createCategory(CategoryDTO categoryDTO) {
		return categoryRepository.save(Category.toModel(categoryDTO)).getId();
	}

}
