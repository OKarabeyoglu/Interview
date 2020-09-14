package com.trendyol.shoppingcard.dto;

import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CartDTO extends BaseDTO{
	private List<CartItemDTO> cartItemList;
	@ApiModelProperty(value = "totalCartAmount", dataType = "MoneyDTO")
	private BigDecimal totalCartAmount;
	@ApiModelProperty(value = "totalCartAmountCurrency", dataType = "String")
	private String totalCartAmountCurrency;

}
