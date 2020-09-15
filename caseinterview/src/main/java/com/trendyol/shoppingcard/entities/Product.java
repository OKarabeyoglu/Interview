package com.trendyol.shoppingcard.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.trendyol.shoppingcard.dto.ProductDTO;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String title;
	private BigDecimal amount;
	private String currencyCode;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category")
	private Category category;

	public static Product toModel(ProductDTO dto) {
		Product model = new Product();
		model.setAmount(dto.getAmount());
		model.setCategory(Category.toModel(dto.getCategory()));
		model.setCurrencyCode(dto.getCurrencyCode());
		model.setTitle(dto.getTitle());
		model.setId(dto.getId());
		model.setCreateDate(dto.getCreateDate());
		model.setModifiedDate(dto.getModifiedDate());
		return model;
	}

	public static ProductDTO toDTO(Product model) {
		ProductDTO dto = null;
		if (model != null) {
			dto = new ProductDTO();
			dto.setId(model.getId());
			dto.setAmount(model.getAmount());
			dto.setCategory(Category.toDTO(model.getCategory()));
			dto.setCurrencyCode(model.getCurrencyCode());
			dto.setTitle(model.getTitle());
			dto.setCreateDate(model.getCreateDate());
			dto.setModifiedDate(model.getModifiedDate());
		}

		return dto;
	}

}