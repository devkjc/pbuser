package com.toy.pbuser.user.service;

import com.toy.pbuser.bird.BirdFeign;
import com.toy.pbuser.common.exception.ProcessException;
import com.toy.pbuser.config.security.SecurityService;
import com.toy.pbuser.postbox.PostBoxFeign;
import com.toy.pbuser.user.domain.User;
import com.toy.pbuser.user.dto.UserDto;
import com.toy.pbuser.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BirdFeign birdFeign;
    private final PostBoxFeign postBoxFeign;

    public Boolean nickNameDuplication(String nickName) {
        return userRepository.countByNickName(nickName) < 1;
    }

    public UserDto.Res login() {
        User user = getFindAuthUser();
        return UserDto.Res.of(user);
    }

    @Transactional
    public UserDto.Res join(UserDto.Req req) {

        User authUser = getAuthUser();
//        authUser.joinUser(req);

        if (nickNameDuplication(req.getNickName())) {
            User user = userRepository.save(authUser);
            return UserDto.Res.of(user);
        } else {
            throw new IllegalArgumentException("닉네임 중복을 다시 확인해주세요.");
        }
    }

    public void deleteMember(String uid) {
//        postBoxFeign.deletePostBox();
//        birdFeign.deleteBird();
        userRepository.deleteById(uid);
    }

    public static User getAuthUser() {
        return SecurityService.getUser();
    }

    public static String getAuthUid() {
        User authUser = getAuthUser();
        return authUser.getUid();
    }

    public User getFindAuthUser() {
        return userRepository.findById(getAuthUid())
                .orElseThrow(() -> new UsernameNotFoundException("회원이 아닙니다. 회원가입이 필요합니다."));
    }

    @Transactional
    public UserDto.Res saveNickname(String uid, UserDto.Req req) {

        String nickName = req.getNickName();

        if (nickNameDuplication(nickName)) {
            Optional<User> byId = userRepository.findById(uid);
            if (byId.isPresent()) {
                User user = byId.get();
                user.setNickName(nickName);
                return UserDto.Res.of(user);
            }else{
                return UserDto.Res.of(userRepository.save(req.toEntity(uid, makeCode())));
            }
        }else {
            throw new ProcessException("중복된 닉네임 입니다.");
        }
    }

    public String makeCode() {
        String code = RandomStringUtils.randomNumeric(12);
        if (userRepository.countByCode(code) > 0) {
            code = makeCode();
        }
        return code;
    }


    public static void main(String[] args) {
        int aa = 0;
        aa++;
        System.out.println("aa++ :: " + aa++);
        System.out.println("++aa :: " + ++aa);
    }
}
