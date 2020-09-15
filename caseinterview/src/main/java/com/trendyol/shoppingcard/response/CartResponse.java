package com.trendyol.shoppingcard.response;

import java.io.Serializable;

import com.trendyol.shoppingcard.dto.CartDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
public class CartResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "cartDTO", dataType = "CartDTO")
	private CartDTO cartDTO;

	public CartResponse(CartDTO cartDTO) {
		this.cartDTO = cartDTO;
	}
}
