package com.trendyol.shoppingcard.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class CategoryDTO extends BaseDTO {
	
	@ApiModelProperty(value = "title", dataType = "String", example = "food")
	private String title;
	@ApiModelProperty(value = "parentCategory", dataType = "Category")
	private CategoryDTO parentCategory;
	@ApiModelProperty(value = "campaignDTOList", dataType = "List<CampaignDTO>")
	private List<CampaignDTO> campaignDTOList;
}
