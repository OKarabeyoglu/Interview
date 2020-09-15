package com.trendyol.shoppingcard.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.shoppingcard.entities.Category;
import com.trendyol.shoppingcard.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Product findOneById(Long id);
	
	List<Product> findByCategory(Category category);
}
