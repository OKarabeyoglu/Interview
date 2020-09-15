package com.trendyol.shoppingcard.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	@Enumerated(EnumType.STRING)
	private DiscountType discountType;
	private Integer minimumProductCount;
	@OneToMany(mappedBy = "campaign", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Category> categoryList;

	public static Campaign toModel(CampaignDTO dto) {
		Campaign model = new Campaign();
		model.setDiscountAmount(dto.getDiscountAmount());
		model.setDiscountType(dto.getDiscountType());
		model.setMinimumProductCount(dto.getMinNumberOfProducts());
		model.setCategoryList(Category.toModelList(dto.getCategoryDTOList()));
		model.setId(dto.getId());
		model.setCreateDate(dto.getCreateDate());
		model.setModifiedDate(dto.getModifiedDate());
		return model;
	}

}
