package com.trendyol.shoppingcard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.shoppingcard.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findOneById(Long id);
}
