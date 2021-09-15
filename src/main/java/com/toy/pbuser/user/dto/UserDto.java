package com.toy.pbuser.user.dto;

import com.toy.pbuser.user.domain.User;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

public class UserDto {

    @Getter
    @ToString
    @ApiModel(value = "UserDto.Req")
    public static class Req {

        private String nickName;

        public User toEntity() {
            return User.builder()
                    .nickName(nickName)
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

        public static UserDto.Res of(User user) {
            return Res.builder()
                    .uid(user.getUid())
                    .nickName(user.getNickName())
                    .build();
        }
    }

}
