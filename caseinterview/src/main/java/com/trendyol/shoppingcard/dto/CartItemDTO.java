package com.trendyol.shoppingcard.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartItemDTO {
	@ApiModelProperty(value = "quantity", dataType = "Integer", example = "2")
	private Integer quantity;
	@ApiModelProperty(value = "product", dataType = "Product")
	private ProductDTO product;
	@ApiModelProperty(value = "totalItemsAmount", dataType = "MoneyDTO")
	private BigDecimal totalItemsAmount;
	@ApiModelProperty(value = "totalItemsAmountCurrency", dataType = "String")
	private String totalItemsAmountCurrency;
	@ApiModelProperty(value = "discountedTotalItemsAmount", dataType = "BigDecimal")
	private BigDecimal discountedTotalItemsAmount;
	@ApiModelProperty(value = "discountedTotalItemsAmount", dataType = "BigDecimal")
	private String discountedTotalItemsAmountCurrency;

}
