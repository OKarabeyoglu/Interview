package com.trendyol.shoppingcard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.shoppingcard.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

	Category findOneById(Long id);
	
	List<Category> findByParentCategory(Category parentCategory); //TODO
}
