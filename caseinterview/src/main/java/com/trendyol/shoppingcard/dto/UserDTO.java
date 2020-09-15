package com.trendyol.shoppingcard.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends BaseDTO{
	
	@ApiModelProperty(value = "name", dataType = "String")
	private String name;
	@ApiModelProperty(value = "surname", dataType = "String")
	private String surname;
	@ApiModelProperty(value = "surname", dataType = "String")
	private String email;
	@ApiModelProperty(value = "cartDTOList", dataType = "List<CartDTO>")
	private List<CartDTO> cartDTOList;

}
