package com.toy.pbuser.user.controller;

import com.toy.pbuser.postbox.PostBoxFeign;
import com.toy.pbuser.user.domain.User;
import com.toy.pbuser.user.dto.UserDto;
import com.toy.pbuser.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
@Api(tags = {"PostBird User API"})
@Validated
public class UserController {

    private final UserService userService;
    private final PostBoxFeign postBoxFeign;

    @GetMapping
    @ApiOperation(value = "GET USER")
    public User getUser() {
        return userService.getFindAuthUser();
    }

    @GetMapping("/id")
    @ApiOperation(value = "GET USERID")
    public ResponseEntity<String> getUId() {
        return ResponseEntity.ok(userService.getFindAuthUser().getUid());
    }

    @GetMapping("/login")
    @ApiOperation(value = "로그인")
    public ResponseEntity<UserDto.Res> login() {
        return ResponseEntity.ok(userService.login());
    }

    @GetMapping("/nickName")
    @ApiOperation(value = "닉네임 중복 검사")
    public ResponseEntity<Boolean> isNicknameDuplicate(@RequestParam @Pattern(regexp = "^[가-힣ㄱ-ㅎa-zA-Z0-9]{2,10}",
                                                                   message = "2~10자의 한글, 영문, 숫자만 사용할 수 있습니다.") String nickName) {
        System.out.println("nickName :: " + nickName);
        return ResponseEntity.ok(userService.nickNameDuplication(nickName));
    }

    @PostMapping("/nickName")
    @ApiOperation(value = "닉네임 저장")
    public ResponseEntity<UserDto.Res> saveNickname(@RequestParam @Pattern(regexp = "^[가-힣ㄱ-ㅎa-zA-Z0-9]{2,10}",
            message = "2~10자의 한글, 영문, 숫자만 사용할 수 있습니다.") String nickName) {
        return ResponseEntity.ok(userService.saveNickname(UserService.getAuthUid(), nickName));
    }

    @GetMapping("/test")
    @ApiOperation(value = "test")
    public ResponseEntity<?> getTest() {
        return ResponseEntity.ok(postBoxFeign.getPostBox());
    }

    @DeleteMapping
    @ApiOperation(value = "회원 탈퇴")
    public ResponseEntity<?> deleteUser() {
        String authUid = UserService.getAuthUid();
        userService.deleteMember(authUid);
        return ResponseEntity.ok().build();
    }

}
