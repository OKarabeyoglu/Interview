package com.trendyol.shoppingcard.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDTO extends BaseDTO{
	
	@ApiModelProperty(value = "name", dataType = "String", example = "Ovunc")
	private String name;
	@ApiModelProperty(value = "surname", dataType = "String", example = "Karabeyoglu")
	private String surname;
	@ApiModelProperty(value = "surname", dataType = "String", example = "ovunckarabeyoglu@gmail.com")
	private String email;
	@ApiModelProperty(value = "cartDTOList", dataType = "List<CartDTO>")
	private List<CartDTO> cartDTOList;

}
