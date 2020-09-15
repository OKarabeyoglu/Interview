package com.trendyol.shoppingcard.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CouponDTO;
import com.trendyol.shoppingcard.entities.Coupon;
import com.trendyol.shoppingcard.generator.MockDataGenerator;
import com.trendyol.shoppingcard.intf.CouponService;
import com.trendyol.shoppingcard.repositories.CouponRepository;

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
		List<Coupon> modelList = MockDataGenerator.createCouponModelList();
		Mockito.when(couponRepository.findAll()).thenReturn(modelList);
		List<CouponDTO> result = controller.getApplicableCoupons();
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testGetApplicableCouponsNoRecord() {
		Mockito.when(couponRepository.findAll()).thenReturn(null);
		List<CouponDTO> result = controller.getApplicableCoupons();
		assertThat(result).isNotNull();
	}
	
	@Test
	public void testCreateCoupon() {
		Coupon model = MockDataGenerator.createCouponModel();
		Mockito.when(couponRepository.save(Mockito.any())).thenReturn(model);
		Long result = controller.createCoupon(Coupon.toDTO(model));
		assertThat(result).isNotNull();
	}

}
