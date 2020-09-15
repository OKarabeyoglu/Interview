package com.trendyol.shoppingcard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trendyol.shoppingcard.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{

}
