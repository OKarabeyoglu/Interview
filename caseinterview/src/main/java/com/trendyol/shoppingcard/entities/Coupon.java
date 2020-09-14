package com.trendyol.shoppingcard.entities;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.trendyol.shoppingcard.util.DiscountType;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Coupon extends BaseEntity<Long>{
	
	private BigDecimal discountAmount;
	private Integer minimumCartAmount;
	private DiscountType discountType;

}
