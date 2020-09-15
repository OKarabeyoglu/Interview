package com.trendyol.shoppingcard.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.trendyol.shoppingcard.dto.UserDTO;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String surname;
	private String email;
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(value = FetchMode.SUBSELECT)
	private List<Cart> cartList;

	public static UserDTO toDTO(User user) {
		if (user == null) {
			return null;
		} else {
			UserDTO dto = UserDTO.builder().cartDTOList(Cart.toDTOList(user.getCartList())).email(user.getEmail())
					.name(user.getName()).surname(user.getSurname()).build();
			dto.setCreateDate(user.getCreateDate());
			dto.setModifiedDate(user.getModifiedDate());
			dto.setId(user.getId());
			return dto;
		}
	}
}
