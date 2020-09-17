package com.trendyol.shoppingcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.shoppingcard.domain.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

	Cart findOneById(Long id);
}
