package com.trendyol.shoppingcard.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel
@Data
public class BaseApiResponse {
	@ApiModelProperty(value = "isSuccesful", dataType = "Boolean")
	private Boolean isSuccesful = Boolean.TRUE;
	
	public BaseApiResponse(Boolean isSuccesful) {
		this.isSuccesful = isSuccesful;
	}

}
