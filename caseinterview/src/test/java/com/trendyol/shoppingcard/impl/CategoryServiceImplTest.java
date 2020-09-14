package com.trendyol.shoppingcard.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CategoryDTO;
import com.trendyol.shoppingcard.entities.Category;
import com.trendyol.shoppingcard.intf.CategoryService;
import com.trendyol.shoppingcard.repositories.CategoryRepository;

public class CategoryServiceImplTest {

	@Mock
	private CategoryRepository categoryRepository;

	@InjectMocks
	private CategoryService controller;
	
	@Test
	public void testCreateCategory() throws Exception {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setTitle("Clothes");
		categoryDTO.setParentCategory(null);
		categoryDTO.setCampaignDTOList(null);
		categoryDTO.setId(1L);
		Category model = Category.toModel(categoryDTO);
		Mockito.when(categoryRepository.save(model)).thenReturn(model);
		Long id = controller.createCategory(categoryDTO);
	    assertEquals(1L, id);
	}
	
	@Test(expected = Exception.class)
	public void testCreateProductDTOIsNull() throws Exception {
		CategoryDTO categoryDTO = null;
		controller.createCategory(categoryDTO);
	}
}
