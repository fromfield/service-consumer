package com.github.service.hystrix;

import com.github.model.User;
import com.github.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceHystrix implements UserService {

	@Override
	public User getUser(Integer id) {
		return new User(101, "HystrixUser", 20);
	}

}
