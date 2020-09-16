package com.trendyol.shoppingcard.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

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
	@NotNull
    @Positive
	@ApiModelProperty(value = "productId", dataType = "Long")
	private Long productId;
	@NotNull
    @Positive
	@ApiModelProperty(value = "quantity", dataType = "Integer")
	private Integer quantity;

}
