package com.trendyol.shoppingcard.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CouponDTO;
import com.trendyol.shoppingcard.entities.Coupon;
import com.trendyol.shoppingcard.intf.CouponService;
import com.trendyol.shoppingcard.repositories.CouponRepository;
import com.trendyol.shoppingcard.util.DiscountType;

@RunWith(BlockJUnit4ClassRunner.class)
public class CouponServiceImplTest {
	
	private CouponRepository couponRepository;

	private CouponService controller;

	@Before
	public void setup() {
		this.couponRepository = Mockito.mock(CouponRepository.class);
		controller = new CouponServiceImpl(couponRepository);
	}
	
	@Test
	public void testGetApplicableCoupons() {
		Coupon model = new Coupon();
		model.setCreateDate(LocalDate.now());
		model.setModifiedDate(LocalDate.now());
		model.setId(1L);
		model.setDiscountAmount(new BigDecimal(50));
		model.setMinimumCartAmount(new BigDecimal(500));
		model.setDiscountType(DiscountType.AMOUNT);
		List<Coupon> modelList = new ArrayList<>();
		modelList.add(model);
		Mockito.when(couponRepository.findByMinimumCartAmountLessThanEqual(new BigDecimal(80))).thenReturn(modelList);
		List<CouponDTO> result = controller.getApplicableCoupons(new BigDecimal(80));
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetApplicableCouponsNoRecord() {
		Mockito.when(couponRepository.findByMinimumCartAmountLessThanEqual(new BigDecimal(80))).thenReturn(null);
		List<CouponDTO> result = controller.getApplicableCoupons(new BigDecimal(80));
		assertThat(result).isNotNull();
	}

}
