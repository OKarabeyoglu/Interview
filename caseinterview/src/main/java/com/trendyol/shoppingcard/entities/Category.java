package com.trendyol.shoppingcard.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import org.springframework.util.CollectionUtils;

import com.trendyol.shoppingcard.dto.CategoryDTO;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Category extends BaseEntity<Long>{
	
	private String title;
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private Category parentCategory;
	
	public static Category toModel(CategoryDTO dto) {
		Category model = new Category();
		model.setId(dto.getId());
		model.setTitle(dto.getTitle());
		if(dto.getParentCategory() != null) {
			model.setParentCategory(toModel(dto.getParentCategory()));
		}
		return model;
	}
	
	public static List<Category> toModelList(List<CategoryDTO> dtoList) {
		List<Category> modelList = new ArrayList<Category>();
		if(!CollectionUtils.isEmpty(dtoList)) {
			for (CategoryDTO dto : dtoList) {
				Category model = new Category();
				model.setId(dto.getId());
				model.setTitle(dto.getTitle());
				if(dto.getParentCategory() != null) {
					model.setParentCategory(toModel(dto.getParentCategory()));
				}
				modelList.add(model);
			}
		}
		return modelList;
	}
	
	public static CategoryDTO toDTO(Category model) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(model.getId());
		dto.setTitle(model.getTitle());
		if(model.getParentCategory() != null) {
			dto.setParentCategory(toDTO(model.getParentCategory()));
		}
		return dto;
	}
	
	public static List<CategoryDTO> toDTOList(List<Category> modelList) {
		List<CategoryDTO> dtoList = new ArrayList<CategoryDTO>();
		if(!CollectionUtils.isEmpty(modelList)) {
			for (Category model : modelList) {
				CategoryDTO dto = new CategoryDTO();
				dto.setId(model.getId());
				dto.setTitle(model.getTitle());
				if(model.getParentCategory() != null) {
					dto.setParentCategory(toDTO(model.getParentCategory()));
				}
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

}
