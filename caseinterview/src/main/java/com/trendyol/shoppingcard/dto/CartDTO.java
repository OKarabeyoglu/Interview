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
	@ApiModelProperty(value = "couponDTO", dataType = "CouponDTO")
	private CouponDTO couponDTO;
	@ApiModelProperty(value = "status", dataType = "Enum")
	private CartStatus status;
	@ApiModelProperty(value = "userDTO", dataType = "UserDTO")
	private UserDTO userDTO;
	@ApiModelProperty(value = "amount", dataType = "BigDecimal")
	private BigDecimal amount = BigDecimal.ZERO;
	@ApiModelProperty(value = "discountedAmount", dataType = "BigDecimal")
	private BigDecimal discountedAmount = BigDecimal.ZERO;

}
