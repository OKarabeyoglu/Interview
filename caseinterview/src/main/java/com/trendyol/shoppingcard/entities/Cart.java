package com.trendyol.shoppingcard.entities;

import java.math.BigDecimal;
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
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.util.CollectionUtils;

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
	private List<CartItem> cartItemList;
	private BigDecimal totalCartAmount;
	private String totalCartAmountCurrency;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "coupon")
	private Coupon coupon;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private User user;
	@Enumerated(EnumType.STRING)
	private CartStatus status;

	public static List<CartDTO> toDTOList(List<Cart> modelList) {
		List<CartDTO> dtoList = new ArrayList<CartDTO>();
		if (!CollectionUtils.isEmpty(modelList)) {
			for (Cart model : modelList) {
				CartDTO dto = new CartDTO();
				dto.setCartItemList(CartItem.toDTOList(model.getCartItemList()));
				dto.setCreateDate(model.getCreateDate());
				dto.setModifiedDate(model.getModifiedDate());
				dto.setId(model.getId());
				dto.setTotalCartAmount(model.getTotalCartAmount());
				dto.setTotalCartAmountCurrency(model.getTotalCartAmountCurrency());
				dto.setCouponDTO(Coupon.toDTO(model.getCoupon()));
				dto.setStatus(model.getStatus());
				dto.setUserDTO(User.toDTO(model.getUser()));
				dtoList.add(dto);
			}
		}
		return dtoList;
	}
}
