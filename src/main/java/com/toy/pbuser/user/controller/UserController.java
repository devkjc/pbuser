package com.toy.pbuser.user.controller;

import com.toy.pbuser.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
@Api(tags = {"PostBird User API"})
public class UserController {

    private final UserService userService;

    @GetMapping("/test")
    @ApiOperation(value = "TEST")
    public ResponseEntity<String> get() {
        return ResponseEntity.ok("TEST ~");
    }

    @GetMapping("/nickname")
    @ApiOperation(value = "닉네임 중복 검사")
    public ResponseEntity<Boolean> isNicknameDuplicate(@RequestParam("nickname") @Valid
                                                           @Pattern(regexp = "/[0-9]|[a-z]|[A-Z]|[가-힣]/",
                                                                   message = "2~10자의 한글, 영문, 숫자만 사용할 수 있습니다.") String nickname) {
        return ResponseEntity.ok(userService.nickNameDuplication(nickname));
    }

}
