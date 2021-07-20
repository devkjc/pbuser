package com.toy.pbuser;

import com.toy.pbuser.config.security.SecurityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableCircuitBreaker
public class PbuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PbuserApplication.class, args);
	}

	@RequestMapping(value = "/user")     // /user 로 매핑
	public Map<String, Object> user() {
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("user", SecurityService.getUser());
		return userInfo;
	}

}
