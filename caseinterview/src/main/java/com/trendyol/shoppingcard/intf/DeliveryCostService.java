package com.trendyol.shoppingcard.intf;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface DeliveryCostService {
	
	public static final BigDecimal FIXED_DELIVERY_COST = new BigDecimal(8.99).setScale(2, RoundingMode.HALF_UP);
	public static final Integer QUANTITY = 5;
	public static final BigDecimal MINIMUM_CART_AMOUNT = new BigDecimal(150).setScale(2, RoundingMode.HALF_UP);
	
	public default BigDecimal calculateDeliveryCost(BigDecimal amount, Integer quantity) {
		BigDecimal deliveryCost = FIXED_DELIVERY_COST;
		if(amount.compareTo(MINIMUM_CART_AMOUNT) > 0 || quantity > QUANTITY) {
			deliveryCost = BigDecimal.ZERO;
		}
		return deliveryCost;
		
	}
}
