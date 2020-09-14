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
		UserDTO userDTO = new UserDTO();
		if (user != null) {
			userDTO.setCreateDate(user.getCreateDate());
			userDTO.setModifiedDate(user.getModifiedDate());
			userDTO.setEmail(user.getEmail());
			userDTO.setId(user.getId());
			userDTO.setSurname(user.getSurname());
			userDTO.setName(user.getName());
			userDTO.setCartDTOList(Cart.toDTOList(user.getCartList()));
		}
		return userDTO;
	}
}
