package com.toy.pbuser.user.service;

import com.toy.pbuser.common.exception.ProcessException;
import com.toy.pbuser.config.security.SecurityService;
import com.toy.pbuser.user.domain.User;
import com.toy.pbuser.user.dto.UserDto;
import com.toy.pbuser.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.valueOf(203), "회원이 아닙니다. 회원가입이 필요합니다."));
    }

    @Transactional
    public UserDto.SimpleRes saveNickname(String uid, String nickname) {
        if (nickNameDuplication(nickname)) {
            Optional<User> byId = userRepository.findById(uid);
            if (byId.isPresent()) {
                User user = byId.get();
                user.setNickname(nickname);
                return UserDto.SimpleRes.of(user);
            }else{
                return UserDto.SimpleRes.of(userRepository.save(User.builder().uid(uid).nickName(nickname).build()));
            }
        }else {
            throw new ProcessException("중복된 닉네임 입니다.");
        }
    }
}
