package com.trendyol.shoppingcard;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.Mockito;

import com.trendyol.shoppingcard.dto.CouponDTO;
import com.trendyol.shoppingcard.intf.CouponService;
import com.trendyol.shoppingcard.request.CouponDTORequest;
import com.trendyol.shoppingcard.response.SaveResponse;
import com.trendyol.shoppingcard.util.DiscountType;

@RunWith(BlockJUnit4ClassRunner.class)
public class CouponApiControllerTest {
	
	private CouponService couponService;

	private CouponApiController controller;

	@Before
	public void setup() {
		this.couponService = Mockito.mock(CouponService.class);
		controller = new CouponApiController(couponService);
	}
	
	@Test
	public void testSaveCoupon() throws Exception {
		CouponDTO couponDTO = new CouponDTO();
		couponDTO.setCreateDate(LocalDate.now());
		couponDTO.setModifiedDate(LocalDate.now());
		couponDTO.setDiscountAmount(new BigDecimal(30));
		couponDTO.setDiscountType(DiscountType.AMOUNT);
		couponDTO.setMinimumCartAmount(new BigDecimal(200));
		couponDTO.setId(1L);
		Mockito.when(couponService.createCoupon(couponDTO)).thenReturn(1L);
		CouponDTORequest request = new CouponDTORequest();
		request.setCouponDTO(couponDTO);
		SaveResponse response = controller.saveCoupon(request);
		assertThat(response).isNotNull();
	}

}
