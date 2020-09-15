package com.trendyol.shoppingcard.entities;

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
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cart")
	private Cart cart;
	
	public static CartItemDTO toDTO(CartItem model) {
		CartItemDTO dto = null;
		if(model != null) {
			dto = new CartItemDTO();
			dto.setProduct(Product.toDTO(model.getProduct()));
			dto.setQuantity(model.getQuantity());
		}
		return dto;
	}

	public static List<CartItemDTO> toDTOList(List<CartItem> modelList) {
		List<CartItemDTO> dtoList = new ArrayList<CartItemDTO>();
		if (!CollectionUtils.isEmpty(modelList)) {
			for (CartItem model : modelList) {
				CartItemDTO dto = new CartItemDTO();
				dto.setProduct(Product.toDTO(model.getProduct()));
				dto.setQuantity(model.getQuantity());
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
}
