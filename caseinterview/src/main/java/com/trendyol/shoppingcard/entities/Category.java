package com.trendyol.shoppingcard.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.util.CollectionUtils;

import com.trendyol.shoppingcard.dto.CategoryDTO;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category extends BaseEntity<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private Category parentCategory;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campaign")
	private Campaign campaign;
	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Product> productList;
	
	public static Category toModel(CategoryDTO dto) {
		Category model = new Category();
		model.setId(dto.getId());
		model.setTitle(dto.getTitle());
		if(dto.getParentCategory() != null) {
			model.setParentCategory(toModel(dto.getParentCategory()));
		}
		model.setCreateDate(dto.getCreateDate());
		model.setModifiedDate(dto.getModifiedDate());
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
				model.setCreateDate(dto.getCreateDate());
				model.setModifiedDate(dto.getModifiedDate());
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
		dto.setCreateDate(model.getCreateDate());
		dto.setModifiedDate(model.getModifiedDate());
		dto.setProductDTOList(Product.toDTOList(model.getProductList()));
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
				dto.setCreateDate(model.getCreateDate());
				dto.setModifiedDate(model.getModifiedDate());
				dto.setProductDTOList(Product.toDTOList(model.getProductList()));
				dtoList.add(dto);
			}
		}
		return dtoList;
	}

}
