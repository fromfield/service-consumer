package com.github.service;

import com.github.service.hystrix.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("user")
@FeignClient(name = "SERVICE-PRODUCER", fallback = UserServiceFallback.class)
public interface UserService extends UserServiceFeign {
}
