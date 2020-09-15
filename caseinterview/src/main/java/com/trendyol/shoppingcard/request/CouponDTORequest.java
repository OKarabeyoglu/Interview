package com.trendyol.shoppingcard.request;

import com.trendyol.shoppingcard.dto.CouponDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
public class CouponDTORequest {
	@ApiModelProperty(value = "couponDTO", dataType = "CouponDTO")
	private CouponDTO couponDTO;
}
