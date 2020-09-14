package com.trendyol.shoppingcard.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trendyol.shoppingcard.dto.UserDTO;
import com.trendyol.shoppingcard.entities.User;
import com.trendyol.shoppingcard.intf.UserService;
import com.trendyol.shoppingcard.repositories.UserRepository;

@Transactional
@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Override
	public UserDTO getSessionUser() {
		//as if taken from session
		Long sessionUserId = 2L;
		return User.toDTO(userRepository.findOneById(sessionUserId));
	}
}
