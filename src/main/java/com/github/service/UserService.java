package com.github.service;

import com.github.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SERVICE-PRODUCER")
public interface UserService {

	@GetMapping("user/{id}")
	User getUser(@PathVariable("id") Integer id);

}
