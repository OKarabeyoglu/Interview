package com.trendyol.shoppingcard.intf;

import java.util.List;

import com.trendyol.shoppingcard.dto.CouponDTO;

public interface CouponService {

	List<CouponDTO> getApplicableCoupons();

	Long createCoupon(CouponDTO couponDTO);

}
