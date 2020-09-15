package com.trendyol.shoppingcard.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.shoppingcard.entities.Cart;
import com.trendyol.shoppingcard.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	CartItem findOneById(Long id);
	
	List<CartItem> findAllByCart(Cart cart);
}
