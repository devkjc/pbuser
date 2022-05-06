package com.toy.pbuser.user.dto;

import com.toy.pbuser.user.domain.User;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.Pattern;

public class UserDto {

    @Getter
    @ToString
    @ApiModel(value = "UserDto.Req")
    public static class Req {

        @Pattern(regexp = "^[가-힣ㄱ-ㅎa-zA-Z0-9]{2,10}",
                message = "2~10자의 한글, 영문, 숫자만 사용할 수 있습니다.")
        private String nickName;

        public User toEntity(String uId, String code) {
            return User.builder()
                    .uid(uId)
                    .nickName(nickName)
                    .code(code)
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "UserDto.Res")
    public static class Res {

        private final String uid;
        private final String nickName;
        private final String code;

        public static UserDto.Res of(User user) {
            return Res.builder()
                    .uid(user.getUid())
                    .nickName(user.getNickName())
                    .code(user.getCode())
                    .build();
        }
    }
}
