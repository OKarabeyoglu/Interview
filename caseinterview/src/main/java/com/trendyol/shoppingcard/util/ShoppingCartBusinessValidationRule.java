package com.trendyol.shoppingcard.util;

import com.trendyol.shoppingcard.exception.BusinessValidationRule;

public enum ShoppingCartBusinessValidationRule implements BusinessValidationRule {

	PRODUCT_TITLE_REQUIRED_ERROR("PRODUCT_TITLE_REQUIRED_ERROR", "Product title is required!"),
	PRODUCT_AMOUNT_REQUIRED_ERROR("PRODUCT_AMOUNT_REQUIRED_ERROR", "Product amount can not be empty or 0.00 TL!"),
	PRODUCT_CURRENCY_REQUIRED_ERROR("PRODUCT_CURRENCY_REQUIRED_ERROR", "Product amount currency is required!");

	private final String code;

	private final String description;

	ShoppingCartBusinessValidationRule(String code, String description) {
		this.code = code;
		this.description = description;
	}

	@Override
	public String getCode() {
		return code;
	}

	@Override
	public String getDescription() {
		return description;
	}

}
