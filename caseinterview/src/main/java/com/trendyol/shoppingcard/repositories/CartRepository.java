package com.trendyol.shoppingcard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.shoppingcard.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	Cart findOneById(Long id);
}
