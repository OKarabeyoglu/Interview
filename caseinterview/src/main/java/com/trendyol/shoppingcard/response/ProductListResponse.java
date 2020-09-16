package com.trendyol.shoppingcard.response;

import java.io.Serializable;
import java.util.List;

import com.trendyol.shoppingcard.dto.ProductDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel
@Getter
@Setter
@NoArgsConstructor
public class ProductListResponse implements Serializable{
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "productDTOList", dataType = "List<ProductDTO>")
	private List<ProductDTO> productDTOList;

	public ProductListResponse(List<ProductDTO> productDTOList) {
		this.productDTOList = productDTOList;
	}
}
