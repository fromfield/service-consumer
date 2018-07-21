package com.github.service.hystrix;

import com.github.model.User;
import com.github.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallback implements UserService {

	@Override
	public String hello() {
		return "Hello UserServiceFallback.";
	}

	@Override
	public User getUser(Integer integer) {
		return new User(101, "UserServiceFallback User", 20);
	}
}
