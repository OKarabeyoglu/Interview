package com.trendyol.shoppingcard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.shoppingcard.entities.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	
}
