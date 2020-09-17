package com.trendyol.shoppingcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.shoppingcard.domain.Cart;
import com.trendyol.shoppingcard.domain.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	CartItem findOneById(Long id);
	
	List<CartItem> findAllByCart(Cart cart);
}
