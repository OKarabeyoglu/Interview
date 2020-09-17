package com.trendyol.shoppingcard.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.trendyol.shoppingcard.dto.CartDTO;
import com.trendyol.shoppingcard.util.CartStatus;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CartItem> cartItemList = new ArrayList<>();
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "coupon")
	private Coupon coupon;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private User user;
	@Enumerated(EnumType.STRING)
	private CartStatus status;

	public static CartDTO toDTO(Cart model) {
		CartDTO dto = null;
		if (model != null) {
			dto = new CartDTO();
			dto.setCartItemList(CartItem.toDTOList(model.getCartItemList()));
			dto.setCreateDate(model.getCreateDate());
			dto.setModifiedDate(model.getModifiedDate());
			dto.setId(model.getId());
			dto.setCouponDTO(Coupon.toDTO(model.getCoupon()));
			dto.setStatus(model.getStatus());
		}
		return dto;
	}
}
