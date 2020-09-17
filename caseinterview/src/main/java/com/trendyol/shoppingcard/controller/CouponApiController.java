package com.trendyol.shoppingcard.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trendyol.shoppingcard.ApiEndpoints;
import com.trendyol.shoppingcard.intf.CouponService;
import com.trendyol.shoppingcard.request.CouponDTORequest;
import com.trendyol.shoppingcard.response.SaveResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = ApiEndpoints.COUPON_API, produces = { ApiEndpoints.RESPONSE_CONTENTTYPE },
        consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
@Api(value = "coupon-api")
public class CouponApiController {
	
private CouponService couponService;
	
	@Autowired
    public CouponApiController(CouponService couponService) {
        this.couponService = couponService;
    }
	
	@PostMapping(value = "/save")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "saveCoupon", notes = "save coupon")
    public SaveResponse saveCoupon(@RequestBody @NotNull CouponDTORequest request) {
        return new SaveResponse(couponService.createCoupon(request.getCouponDTO()));
    }

}
