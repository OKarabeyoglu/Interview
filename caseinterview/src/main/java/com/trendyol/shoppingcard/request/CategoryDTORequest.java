package com.trendyol.shoppingcard.request;

import com.trendyol.shoppingcard.dto.CategoryDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
public class CategoryDTORequest {
	@ApiModelProperty(value = "categoryDTO", dataType = "CategoryDTO")
	private CategoryDTO categoryDTO;

}
