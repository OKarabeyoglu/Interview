package com.trendyol.shoppingcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.shoppingcard.domain.Category;
import com.trendyol.shoppingcard.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	Product findOneById(Long id);
	
	List<Product> findAllByCategoryIn(List<Category> categoryList);
	
}
