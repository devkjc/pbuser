package com.toy.pbuser.bird.controller;

import com.toy.pbuser.bird.dto.BirdDto;
import com.toy.pbuser.bird.service.BirdService;
import com.toy.pbuser.user.dto.UserDto;
import com.toy.pbuser.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/api/v1/bird")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
@Api(tags = {"Bird API"})
@Validated
public class BirdController {

    private final BirdService birdService;

    @GetMapping("/name")
    @ApiOperation(value = "새이름 중복 검사")
    public ResponseEntity<Boolean> isBirdNameDuplicate(@RequestParam @Pattern(regexp = "^[가-힣ㄱ-ㅎa-zA-Z0-9]{2,10}",
                                                                   message = "2~10자의 한글, 영문, 숫자만 사용할 수 있습니다.") String birdName) {
        return ResponseEntity.ok(birdService.birdNameDuplication(birdName));
    }

    @PostMapping("/name")
    @ApiOperation(value = "새이름 저장")
    public ResponseEntity<BirdDto.Res> saveBirdName(@Valid @RequestBody BirdDto.Req req) {
        return ResponseEntity.ok(birdService.saveBirdName(req));
    }

}
