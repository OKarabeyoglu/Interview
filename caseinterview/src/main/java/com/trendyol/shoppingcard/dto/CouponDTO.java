package com.trendyol.shoppingcard.dto;

import java.math.BigDecimal;

import com.trendyol.shoppingcard.util.DiscountType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CouponDTO extends BaseDTO {
	@ApiModelProperty(value = "discountAmount", dataType = "BigDecimal")
	private BigDecimal discountAmount;
	@ApiModelProperty(value = "minimumCartAmount", dataType = "BigDecimal")
	private BigDecimal minimumCartAmount;
	@ApiModelProperty(value = "discountType", dataType = "DiscountType")
	private DiscountType discountType;
}
