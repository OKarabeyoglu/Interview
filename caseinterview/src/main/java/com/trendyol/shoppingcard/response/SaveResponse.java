package com.trendyol.shoppingcard.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@ApiModel
@Getter
@Setter
public class SaveResponse implements Serializable{
	@ApiModelProperty(value = "id", dataType = "Long")
	private Long id;

	public SaveResponse(Long id) {
		this.id = id;
	}
}
