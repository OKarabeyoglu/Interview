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
public class ShowCartRequest {
	
	@ApiModelProperty(value = "cartId", dataType = "Id")
	private Long cartId;

}
