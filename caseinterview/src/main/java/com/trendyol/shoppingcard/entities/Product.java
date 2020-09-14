package com.trendyol.shoppingcard.entities;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.trendyol.shoppingcard.dto.ProductDTO;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private BigDecimal amount;
	private String currencyCode;
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Category category;

	public static Product toModel(ProductDTO dto) {
		Product model = new Product();
		model.setAmount(dto.getAmount());
		model.setCategory(Category.toModel(dto.getCategory()));
		model.setCurrencyCode(dto.getCurrencyCode());
		model.setTitle(dto.getTitle());
		model.setId(dto.getId());
		return model;
	}
	
//	public static Product toUpdateModel(Product model, ProductDTO dto) {
//		model.setAmount(dto.getAmount() != null ? dto.getAmount() : model.getAmount());
//		model.setCategory(dto.getCategory() != null ? Category.toModel(dto.getCategory()) : model.getCategory());
//		model.setCurrencyCode(dto.getCurrencyCode() != null ? dto.getCurrencyCode() : model.getCurrencyCode());
//		model.setTitle(dto.getTitle() != null ? dto.getCurrencyCode() : model.getCurrencyCode());
//		return model;
//	}
	
	public static ProductDTO toDTO(Product model) {
		ProductDTO dto = new ProductDTO();
		dto.setId(model.getId());
		dto.setAmount(model.getAmount());
		dto.setCategory(Category.toDTO(model.getCategory()));
		dto.setCurrencyCode(model.getCurrencyCode());
		dto.setTitle(model.getTitle());
		return dto;
	}

}