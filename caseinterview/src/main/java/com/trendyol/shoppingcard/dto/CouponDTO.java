package com.trendyol.shoppingcard.dto;

import java.math.BigDecimal;

import com.trendyol.shoppingcard.util.DiscountType;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CouponDTO extends BaseDTO {
	@ApiModelProperty(value = "discountAmount", dataType = "BigDecimal", example = "2.00")
	private BigDecimal discountAmount;
	@ApiModelProperty(value = "minimumCartAmount", dataType = "BigDecimal", example = "10.00")
	private BigDecimal minimumCartAmount;
	@ApiModelProperty(value = "discountType", dataType = "DiscountType", example = "RATE")
	private DiscountType discountType;
}
