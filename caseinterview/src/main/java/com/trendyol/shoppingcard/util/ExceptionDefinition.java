package com.trendyol.shoppingcard.util;

public enum ExceptionDefinition {
	PRODUCT_DTO_CAN_NOT_BE_NULL_ERROR("Ürün Bilgileri boş olamaz!"),
	PRODUCT_ID_CAN_NOT_BE_NULL_ERROR("Ürün Bilgisi bulunamadı!"),
	PRODUCT_IS_IN_THE_CHART_ERROR("Bu işlemi gerçekleştirebilmek için ürünün sepetten çıkarılması gerekmektedir.");
	private String exceptionDefinition;
	
	private ExceptionDefinition(String exceptionDefinition) {
        this.exceptionDefinition = exceptionDefinition;
    }
	
	public String getExcetionDefinition() {
        return exceptionDefinition;
    }

}
