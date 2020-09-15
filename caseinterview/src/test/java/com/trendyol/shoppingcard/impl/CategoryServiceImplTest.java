package com.trendyol.shoppingcard.impl;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CategoryDTO;
import com.trendyol.shoppingcard.entities.Category;
import com.trendyol.shoppingcard.generator.MockDataGenerator;
import com.trendyol.shoppingcard.intf.CategoryService;
import com.trendyol.shoppingcard.repositories.CategoryRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class CategoryServiceImplTest {

	private CategoryRepository categoryRepository;
	private CategoryService controller;

	@Before
	public void setup() {
		this.categoryRepository = Mockito.mock(CategoryRepository.class);
		controller = new CategoryServiceImpl(categoryRepository);
	}

	@Test
	public void testCreateCategory() throws Exception {
		CategoryDTO categoryDTO = MockDataGenerator.createCategoryDTO();
		Category model = Category.toModel(categoryDTO);
		Mockito.when(categoryRepository.save(Mockito.any())).thenReturn(model);
		Long id = controller.createCategory(categoryDTO);
		assertThat(id).isNotNull();
	}

	@Test(expected = Exception.class)
	public void testCreateCategoryDTOIsNull() throws Exception {
		CategoryDTO categoryDTO = null;
		controller.createCategory(categoryDTO);
	}
}
