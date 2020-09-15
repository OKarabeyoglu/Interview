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
	@ApiModelProperty(value = "discountAmount", dataType = "BigDecimal")
    private BigDecimal discountAmount;
	@ApiModelProperty(value = "minNumberOfProducts", dataType = "Integer")
    private Integer minNumberOfProducts;
	@ApiModelProperty(value = "discountType", dataType = "DiscountType")
    private DiscountType discountType;
}
