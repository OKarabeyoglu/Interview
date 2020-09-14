package com.trendyol.shoppingcard.request;

import com.trendyol.shoppingcard.dto.ProductDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel
@Data
@NoArgsConstructor
public class ProductDTORequest {
	@ApiModelProperty(value = "productDTO", dataType = "ProductDTO")
	private ProductDTO productDTO;

}
