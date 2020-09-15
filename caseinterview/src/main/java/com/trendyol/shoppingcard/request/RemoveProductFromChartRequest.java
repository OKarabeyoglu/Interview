package com.trendyol.shoppingcard.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
public class RemoveProductFromChartRequest {
	
	@ApiModelProperty(value = "cartItemId", dataType = "Long")
	private Long cartItemId;

}
