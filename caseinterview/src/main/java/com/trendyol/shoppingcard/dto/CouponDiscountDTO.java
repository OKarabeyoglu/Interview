package com.trendyol.shoppingcard.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CouponDiscountDTO {
	
	@ApiModelProperty(value = "couponId", dataType = "Long")
	private Long couponId;
	@ApiModelProperty(value = "BigDecimal", dataType = "discountAmount")
	private BigDecimal discountAmount;

}
