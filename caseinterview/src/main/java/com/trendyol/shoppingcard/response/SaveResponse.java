package com.trendyol.shoppingcard.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@NoArgsConstructor
public class SaveResponse implements Serializable{
	@ApiModelProperty(value = "id", dataType = "Long")
	private Long id;

	public SaveResponse(Long id) {
		this.id = id;
	}
}
