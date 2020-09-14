package com.trendyol.shoppingcard;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CategoryDTO;
import com.trendyol.shoppingcard.intf.CategoryService;
import com.trendyol.shoppingcard.request.CategoryDTORequest;
import com.trendyol.shoppingcard.response.SaveResponse;

public class CategoryApiControllerTest {

	private CategoryService categoryService;

	private CategoryApiController controller;

	@Before
	public void setup() {
		this.categoryService = Mockito.mock(CategoryService.class);
		controller = new CategoryApiController(categoryService);
	}

	@Test
	public void testSaveCategory() throws Exception {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setTitle("Clothes");
		categoryDTO.setParentCategory(null);
		categoryDTO.setCampaignDTOList(null);
		categoryDTO.setId(1L);
		Mockito.when(categoryService.createCategory(categoryDTO)).thenReturn(1L);
		CategoryDTORequest request = new CategoryDTORequest();
		request.setCategoryDTO(categoryDTO);
		SaveResponse response = controller.saveCategory(request);
		assertThat(response, is(notNullValue()));
	}
}
