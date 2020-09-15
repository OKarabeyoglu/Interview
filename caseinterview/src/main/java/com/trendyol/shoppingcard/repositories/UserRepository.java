package com.trendyol.shoppingcard.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.shoppingcard.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findOneById(Long id);

}
