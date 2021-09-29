package com.toy.pbuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableCircuitBreaker
@EnableJpaAuditing
public class PbuserApplication {

	public static void main(String[] args) {
		SpringApplication.run(PbuserApplication.class, args);
	}

//	@GetMapping(value = "/user")     // /user 로 매핑
//	@ApiOperation(value = "getAuth API")
//	public User getAuth() {
//		return SecurityService.getUser();
//	}

}
