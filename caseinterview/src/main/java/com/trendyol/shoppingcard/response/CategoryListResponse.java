package com.trendyol.shoppingcard.response;

import java.io.Serializable;
import java.util.List;

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
public class CategoryListResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "categoryDTOList", dataType = "List<CategoryDTO>")
	private List<CategoryDTO> categoryDTOList;

	public CategoryListResponse(List<CategoryDTO> categoryDTOList) {
		this.categoryDTOList = categoryDTOList;
	}

}
