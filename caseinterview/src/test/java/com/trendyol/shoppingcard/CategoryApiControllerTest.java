package com.trendyol.shoppingcard;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.controller.CategoryApiController;
import com.trendyol.shoppingcard.dto.CategoryDTO;
import com.trendyol.shoppingcard.generator.MockDataGenerator;
import com.trendyol.shoppingcard.intf.CategoryService;
import com.trendyol.shoppingcard.request.CategoryDTORequest;
import com.trendyol.shoppingcard.response.CategoryListResponse;
import com.trendyol.shoppingcard.response.SaveResponse;

@RunWith(BlockJUnit4ClassRunner.class)
public class CategoryApiControllerTest {

	private CategoryService categoryService;

	private CategoryApiController controller;

	@Before
	public void setup() {
		this.categoryService = Mockito.mock(CategoryService.class);
		controller = new CategoryApiController(categoryService);
	}

	@Test
	public void testSaveCategory() {
		CategoryDTO categoryDTO = MockDataGenerator.createCategoryDTO();
		Mockito.when(categoryService.createCategory(categoryDTO)).thenReturn(1L);
		CategoryDTORequest request = new CategoryDTORequest();
		request.setCategoryDTO(categoryDTO);
		SaveResponse response = controller.saveCategory(request);
		assertThat(response).isNotNull();
	}
	
	@Test
	public void testGetCategoriesByParentCategory() {
		Mockito.when(categoryService.getCategoriesByParentCategory(1L)).thenReturn(null);
		CategoryListResponse response = new CategoryListResponse();
		response.setCategoryDTOList(null);
		response.getCategoryDTOList();
		response = controller.getCategoriesByParentCategory(1L);
		assertThat(response).isNotNull();
	}
}
