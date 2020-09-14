package com.trendyol.shoppingcard.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.util.CollectionUtils;

import com.trendyol.shoppingcard.dto.CartItemDTO;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem extends BaseEntity<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer quantity;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product")
	private Product product;
	private BigDecimal totalItemsAmount;
	private String totalItemsAmountCurrency;
	private BigDecimal discountedTotalItemsAmount;
	private String discountedTotalItemsAmountCurrency;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart")
	private Cart cart;

	public static List<CartItemDTO> toDTOList(List<CartItem> modelList) {
		List<CartItemDTO> dtoList = new ArrayList<CartItemDTO>();
		if (!CollectionUtils.isEmpty(modelList)) {
			for (CartItem model : modelList) {
				CartItemDTO dto = new CartItemDTO();
				dto.setDiscountedTotalItemsAmount(model.getDiscountedTotalItemsAmount());
				dto.setDiscountedTotalItemsAmountCurrency(model.getDiscountedTotalItemsAmountCurrency());
				dto.setProduct(Product.toDTO(model.getProduct()));
				dto.setQuantity(model.getQuantity());
				dto.setTotalItemsAmount(model.getTotalItemsAmount());
				dto.setTotalItemsAmountCurrency(model.getDiscountedTotalItemsAmountCurrency());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
}
