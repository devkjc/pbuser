package com.toy.pbuser.postbox.controller;

import com.toy.pbuser.postbox.dto.PostBoxDto;
import com.toy.pbuser.postbox.service.PostBoxService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/postbox")
@RequiredArgsConstructor
@Log4j2
@CrossOrigin
@Api(tags = {"PostBox API"})
public class PostBoxController {

    private final PostBoxService postBoxService;

    @PostMapping
    @ApiOperation(value = "우체통 설치")
    public ResponseEntity<PostBoxDto.Res> savePostBox(@RequestBody PostBoxDto.Req req) {
        return ResponseEntity.ok(postBoxService.savePostBox(req));
    }

}
