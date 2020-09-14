package com.trendyol.shoppingcard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trendyol.shoppingcard.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	User findOneById(Long id);

}
