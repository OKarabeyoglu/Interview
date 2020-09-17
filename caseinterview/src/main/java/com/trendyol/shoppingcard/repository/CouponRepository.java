package com.trendyol.shoppingcard.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trendyol.shoppingcard.domain.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{
	Coupon findOneById(Long id);
	
	List<Coupon> findByMinimumCartAmountLessThanEqual(BigDecimal cartAmount);
}
