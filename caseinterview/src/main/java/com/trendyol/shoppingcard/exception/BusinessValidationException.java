package com.trendyol.shoppingcard.exception;

import com.trendyol.shoppingcard.util.ShoppingCartBusinessValidationRule;

public class BusinessValidationException extends BaseException {
	private static final long serialVersionUID = -8434398516786320374L;

	private final ShoppingCartBusinessValidationRule rule;

	public BusinessValidationException(ShoppingCartBusinessValidationRule rule) {
		this(rule, rule.getDescription());
	}

	public BusinessValidationException(ShoppingCartBusinessValidationRule rule, String message) {
		super(message);
		this.rule = rule;
	}

}