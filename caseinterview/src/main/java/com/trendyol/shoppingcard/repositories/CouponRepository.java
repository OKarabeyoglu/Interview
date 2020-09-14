package com.trendyol.shoppingcard.repositories;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trendyol.shoppingcard.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{

	List<Coupon> findByMinimumCartAmountLessThanEqual(BigDecimal cartAmount);
}
