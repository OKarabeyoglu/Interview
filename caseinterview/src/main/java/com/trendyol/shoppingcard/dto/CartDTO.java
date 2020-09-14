package com.trendyol.shoppingcard.dto;

import java.math.BigDecimal;
import java.util.List;

import com.trendyol.shoppingcard.util.CartStatus;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CartDTO extends BaseDTO{
	private List<CartItemDTO> cartItemList;
	@ApiModelProperty(value = "totalCartAmount", dataType = "MoneyDTO", example = "100.00")
	private BigDecimal totalCartAmount;
	@ApiModelProperty(value = "totalCartAmountCurrency", dataType = "String", example = "TL")
	private String totalCartAmountCurrency;
	@ApiModelProperty(value = "couponDTO", dataType = "CouponDTO")
	private CouponDTO couponDTO;
	@ApiModelProperty(value = "status", dataType = "Enum", example = "ACTIVE")
	private CartStatus status;
	@ApiModelProperty(value = "userDTO", dataType = "UserDTO")
	private UserDTO userDTO;

}
