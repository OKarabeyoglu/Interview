package com.trendyol.shoppingcard.intf;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface DeliveryCostService {
	
	public static final BigDecimal FIXED_DELIVERY_COST = new BigDecimal(8.99).setScale(2, RoundingMode.HALF_UP);
	
	public default BigDecimal calculateDeliveryCost(BigDecimal amount, Integer quantity) {
		BigDecimal deliveryCost = FIXED_DELIVERY_COST;
		if(amount.compareTo(new BigDecimal(100)) > 0 || quantity > 5) {
			deliveryCost = BigDecimal.ZERO;
		}
		return deliveryCost;
		
	}
}
