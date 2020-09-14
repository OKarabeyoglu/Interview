package com.trendyol.shoppingcard.dto;

import java.math.BigDecimal;
import java.util.List;

import com.trendyol.shoppingcard.util.DiscountType;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CampaignDTO extends BaseDTO{
	@ApiModelProperty(value = "categoryDTOList", dataType = "List<CategoryDTO>")
    private List<CategoryDTO> categoryDTOList;
	@ApiModelProperty(value = "discountAmount", dataType = "BigDecimal", example = "1.00")
    private BigDecimal discountAmount;
	@ApiModelProperty(value = "minNumberOfProducts", dataType = "Integer", example = "1")
    private Integer minNumberOfProducts;
	@ApiModelProperty(value = "discountType", dataType = "DiscountType", example = "RATE")
    private DiscountType discountType;
}
