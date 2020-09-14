package com.trendyol.shoppingcard.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.trendyol.shoppingcard.util.DiscountType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Coupon extends BaseEntity<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal discountAmount;
	private Integer minimumCartAmount;
	private DiscountType discountType;

}
