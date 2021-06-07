package com.toy.pbuser.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
@Api(tags = {"PostBird User API"})
public class UserController {

    @GetMapping("/test")
    @ApiOperation(value = "TEST")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("TEST");
    }

}
