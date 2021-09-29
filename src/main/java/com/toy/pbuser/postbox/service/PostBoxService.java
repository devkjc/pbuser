package com.toy.pbuser.postbox.service;

import com.toy.pbuser.postbox.dto.PostBoxDto;
import com.toy.pbuser.postbox.repository.PostBoxRepository;
import com.toy.pbuser.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostBoxService {

    private final PostBoxRepository postBoxRepository;
    private final UserService userService;

    public PostBoxDto.Res savePostBox(PostBoxDto.Req req) {
        return PostBoxDto.Res.of(postBoxRepository.save(req.toEntity(userService.getFindAuthUser())));
    }
}
