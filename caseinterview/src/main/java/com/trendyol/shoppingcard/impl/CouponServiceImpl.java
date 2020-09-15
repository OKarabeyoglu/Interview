package com.trendyol.shoppingcard.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trendyol.shoppingcard.dto.CouponDTO;
import com.trendyol.shoppingcard.entities.Coupon;
import com.trendyol.shoppingcard.intf.CouponService;
import com.trendyol.shoppingcard.repositories.CouponRepository;

@Transactional
@Service
public class CouponServiceImpl implements CouponService{
	
	private CouponRepository couponRepository;
	
	@Autowired
	public CouponServiceImpl(CouponRepository couponRepository) {
		this.couponRepository = couponRepository;
	}
	
	@Override
	public List<CouponDTO> getApplicableCoupons(BigDecimal cartAmount) {
		List<Coupon> couponList = couponRepository.findByMinimumCartAmountLessThanEqual(cartAmount);
		return Coupon.toDTOList(couponList);
	}
	
	@Override
	public Long createCoupon(CouponDTO couponDTO) {
		return couponRepository.save(Coupon.toModel(couponDTO)).getId();
	}

}
