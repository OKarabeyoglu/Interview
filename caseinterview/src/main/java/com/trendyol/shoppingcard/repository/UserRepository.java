package com.trendyol.shoppingcard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trendyol.shoppingcard.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findOneById(Long id);

}
