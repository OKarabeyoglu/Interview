package com.trendyol.shoppingcard.request;

import com.trendyol.shoppingcard.dto.CategoryDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@NoArgsConstructor
public class CategoryDTORequest {
	@ApiModelProperty(value = "categoryDTO", dataType = "CategoryDTO")
	private CategoryDTO categoryDTO;

}
