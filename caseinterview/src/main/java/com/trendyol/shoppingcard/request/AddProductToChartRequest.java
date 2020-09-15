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
public class AddProductToChartRequest {
	
	@ApiModelProperty(value = "productId", dataType = "Long")
	private Long productId;
	@ApiModelProperty(value = "quantity", dataType = "Integer")
	private Integer quantity;

}
