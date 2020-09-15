package com.trendyol.shoppingcard.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
public class ShowCartResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "cart", dataType = "String")
	private String cart;

	public ShowCartResponse(String cart) {
		this.cart = cart;
	}

}
