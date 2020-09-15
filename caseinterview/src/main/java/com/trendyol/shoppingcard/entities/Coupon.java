package com.trendyol.shoppingcard.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.util.CollectionUtils;

import com.trendyol.shoppingcard.dto.CouponDTO;
import com.trendyol.shoppingcard.util.DiscountType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Coupon extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal discountAmount;
	private BigDecimal minimumCartAmount;
	@Enumerated(EnumType.STRING)
	private DiscountType discountType;
	
	public static Coupon toModel(CouponDTO dto) {
		Coupon model = new Coupon();
		model.setId(dto.getId());
		model.setCreateDate(dto.getCreateDate());
		model.setModifiedDate(dto.getModifiedDate());
		model.setDiscountAmount(dto.getDiscountAmount());
		model.setMinimumCartAmount(dto.getMinimumCartAmount());
		model.setDiscountType(dto.getDiscountType());
		return model;
	}
	
	public static CouponDTO toDTO(Coupon model) {
		CouponDTO dto = null;	
		if (model != null) {
		    dto = new CouponDTO();
			dto.setId(model.getId());
			dto.setCreateDate(model.getCreateDate());
			dto.setModifiedDate(model.getModifiedDate());
			dto.setDiscountAmount(model.getDiscountAmount());
			dto.setMinimumCartAmount(model.getMinimumCartAmount());
			dto.setDiscountType(model.getDiscountType());
		}
		return dto;
	}

	public static List<CouponDTO> toDTOList(List<Coupon> modelList) {
		List<CouponDTO> dtoList = new ArrayList<CouponDTO>();
		if (!CollectionUtils.isEmpty(modelList)) {
			for (Coupon model : modelList) {
				CouponDTO dto = new CouponDTO();
				dto.setId(model.getId());
				dto.setCreateDate(model.getCreateDate());
				dto.setModifiedDate(model.getModifiedDate());
				dto.setDiscountAmount(model.getDiscountAmount());
				dto.setMinimumCartAmount(model.getMinimumCartAmount());
				dto.setDiscountType(model.getDiscountType());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
}
