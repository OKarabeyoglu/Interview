package com.trendyol.shoppingcard.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.trendyol.shoppingcard.dto.CampaignDTO;
import com.trendyol.shoppingcard.util.DiscountType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Campaign extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BigDecimal discountAmount;

	private DiscountType discountType;

	private Integer minimumProductCount;
	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
	private List<Category> categoryList;

	public static Campaign toModel(CampaignDTO dto) {
		Campaign model = new Campaign();
		model.setDiscountAmount(dto.getDiscountAmount());
		model.setDiscountType(dto.getDiscountType());
		model.setMinimumProductCount(dto.getMinNumberOfProducts());
		model.setCategoryList(Category.toModelList(dto.getCategoryDTOList()));
		model.setId(dto.getId());
		return model;
	}

	public static CampaignDTO toDTO(Campaign model) {
		CampaignDTO dto = new CampaignDTO();
		dto.setDiscountAmount(model.getDiscountAmount());
		dto.setDiscountType(model.getDiscountType());
		dto.setMinNumberOfProducts(model.getMinimumProductCount());
		dto.setCategoryDTOList(Category.toDTOList(model.getCategoryList()));
		dto.setId(model.getId());
		return dto;
	}

}
