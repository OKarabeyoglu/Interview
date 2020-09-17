package com.trendyol.shoppingcard.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemDTO extends BaseDTO{
	@ApiModelProperty(value = "quantity", dataType = "Integer")
	private Integer quantity;
	@ApiModelProperty(value = "product", dataType = "Product")
	private ProductDTO product;
	@ApiModelProperty(value = "amount", dataType = "BigDecimal")
	private BigDecimal amount = BigDecimal.ZERO;
	@ApiModelProperty(value = "discountedAmount", dataType = "BigDecimal")
	private BigDecimal discountedAmount = BigDecimal.ZERO;
	

}
