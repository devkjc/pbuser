package com.toy.pbuser.user.dto;

import com.toy.pbuser.bird.dto.BirdDto;
import com.toy.pbuser.postbox.dto.PostBoxDto;
import com.toy.pbuser.user.domain.User;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

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
        private final List<BirdDto.SimpleRes> birdList;
        private final List<PostBoxDto.SimpleRes> postBoxList;

        public static UserDto.Res of(User user) {
            return Res.builder()
                    .uid(user.getUid())
                    .birdList(user.getBirdSimpleResList())
                    .nickName(user.getNickName())
                    .postBoxList(user.getPostBoxSimpleResList())
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "UserDto.SimpleRes")
    public static class SimpleRes {

        private final String uid;
        private final String nickName;

        public static UserDto.SimpleRes of(User user) {
            return SimpleRes.builder()
                    .uid(user.getUid())
                    .nickName(user.getNickName())
                    .build();
        }
    }

}
