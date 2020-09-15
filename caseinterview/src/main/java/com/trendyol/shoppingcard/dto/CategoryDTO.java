package com.trendyol.shoppingcard.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDTO extends BaseDTO {
	
	@ApiModelProperty(value = "title", dataType = "String")
	private String title;
	@ApiModelProperty(value = "parentCategory", dataType = "Category")
	private CategoryDTO parentCategory;
	@ApiModelProperty(value = "campaignDTOList", dataType = "List<CampaignDTO>")
	private List<CampaignDTO> campaignDTOList;
	@ApiModelProperty(value = "productDTOList", dataType = "List<ProductDTO>")
	private List<ProductDTO> productDTOList;
	
}
