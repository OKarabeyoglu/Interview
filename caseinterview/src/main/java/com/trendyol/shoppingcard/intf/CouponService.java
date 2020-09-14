package com.trendyol.shoppingcard.intf;

import java.math.BigDecimal;
import java.util.List;

import com.trendyol.shoppingcard.dto.CouponDTO;

public interface CouponService {

	List<CouponDTO> getApplicableCoupons(BigDecimal cartAmount);

}
