package com.toy.pbuser.bird.service;

import com.toy.pbuser.bird.dto.BirdDto;
import com.toy.pbuser.bird.repository.BirdRepository;
import com.toy.pbuser.common.exception.ProcessException;
import com.toy.pbuser.user.domain.User;
import com.toy.pbuser.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BirdService {

    private final BirdRepository birdRepository;
    private final UserService userService;

    public Boolean birdNameDuplication(String birdName) {
        return birdRepository.countByBirdName(birdName) < 1;
    }

    @Transactional
    public BirdDto.Res saveBirdName(BirdDto.Req req) {

        User user = userService.getFindAuthUser();

        if (birdNameDuplication(req.getBirdName())) {
            return BirdDto.Res.of(birdRepository.save(req.toEntity(user)));
        }else {
            throw new ProcessException("중복된 새이름 입니다.");
        }
    }

}
