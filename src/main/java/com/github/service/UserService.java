package com.github.service;

import com.github.service.hystrix.UserServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "SERVICE-PRODUCER", fallback = UserServiceFallback.class)
public interface UserService extends UserServiceFeign {
}
