package com.trendyol.shoppingcard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trendyol.shoppingcard.ApiEndpoints;
import com.trendyol.shoppingcard.intf.CategoryService;
import com.trendyol.shoppingcard.request.CategoryDTORequest;
import com.trendyol.shoppingcard.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = ApiEndpoints.CATEGORY_API, produces = { ApiEndpoints.RESPONSE_CONTENTTYPE }, consumes = {
		MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Api(value = "category-api")
public class CategoryApiController {

	private CategoryService categoryService;

	@Autowired
	public CategoryApiController(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	@PostMapping(value = "/save")
	@ResponseStatus(HttpStatus.OK)
	@ApiOperation(value = "saveCategory", notes = "save category")
	public SaveResponse saveCategory(@RequestBody CategoryDTORequest request) {
		return new SaveResponse(categoryService.createCategory(request.getCategoryDTO()));
	}

}
