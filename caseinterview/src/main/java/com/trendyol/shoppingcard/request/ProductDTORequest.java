package com.trendyol.shoppingcard.request;

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
public class ProductDTORequest {
	@ApiModelProperty(value = "productDTO", dataType = "ProductDTO")
	private ProductDTO productDTO;

}
