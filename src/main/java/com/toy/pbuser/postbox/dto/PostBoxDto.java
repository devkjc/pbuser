package com.toy.pbuser.postbox.dto;

import com.toy.pbuser.common.domain.Address;
import com.toy.pbuser.postbox.domain.PostBox;
import com.toy.pbuser.user.domain.User;
import com.toy.pbuser.user.dto.UserDto;
import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import java.math.BigDecimal;

public class PostBoxDto {

    @Getter
    @ToString
    @ApiModel(value = "PostBoxDto.Req")
    public static class Req {

        private String zipcode;
        private String address1;
        private String address2;
        private BigDecimal longitude;
        private BigDecimal latitude;

        public PostBox toEntity(User user) {
            return PostBox.builder()
                    .user(user)
                    .address(Address.builder().address1(address1).address2(address2).zipcode(zipcode).latitude(latitude).longitude(longitude).build())
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "PostBoxDto.Res")
    public static class Res {

        private long id;
        private UserDto.SimpleRes user;
        private Address address;

        public static Res of(PostBox postBox) {
            return Res.builder()
                    .id(postBox.getId())
                    .user(postBox.getUser().getSimpleUser())
                    .address(postBox.getAddress())
                    .build();
        }
    }

    @Getter
    @Builder
    @ToString
    @ApiModel(value = "PostBoxDto.SimpleRes")
    public static class SimpleRes {

        private long id;
        private Address address;

        public static SimpleRes of(PostBox postBox) {
            return SimpleRes.builder()
                    .id(postBox.getId())
                    .address(postBox.getAddress())
                    .build();
        }
    }

}
