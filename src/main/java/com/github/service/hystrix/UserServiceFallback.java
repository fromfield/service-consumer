package com.github.service.hystrix;

import com.github.model.User;
import com.github.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallback implements UserService {

	@Override
	public String hello() {
		return "Hello Hystrix Fallback.";
	}

	@Override
	public User getUser(Integer id) {
		return new User(101, "HystrixUserFallback", 20);
	}
}
