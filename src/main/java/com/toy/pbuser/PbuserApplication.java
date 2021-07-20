package com.toy.pbuser;

import com.toy.pbuser.config.security.SecurityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@EnableFeignClients
@EnableCircuitBreaker
@Api(tags = {"Auth User"})
public class PbuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PbuserApplication.class, args);
	}

	@GetMapping(value = "/user")     // /user 로 매핑
	@ApiOperation(value = "getAuth API")
	public Map<String, Object> getAuth() {
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("user", SecurityService.getUser());
		return userInfo;
	}

}
