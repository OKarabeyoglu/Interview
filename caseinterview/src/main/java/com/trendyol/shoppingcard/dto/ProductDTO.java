package com.trendyol.shoppingcard.dto;

import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO extends BaseDTO{
	@ApiModelProperty(value = "title", dataType = "String", example = "Apple")
	private String title;
	@ApiModelProperty(value = "amount", dataType = "BigDecimal", example = "5.00")
	private BigDecimal amount;
	@ApiModelProperty(value = "currencyCode", dataType = "String", example = "TL")
	private String currencyCode;
	@ApiModelProperty(value = "category", dataType = "Category")
	private CategoryDTO category;
}
