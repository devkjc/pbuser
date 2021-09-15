package com.toy.pbuser.user.domain;

import com.toy.pbuser.common.domain.BaseTimeEntity;
import com.toy.pbuser.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    private String uid;

    @Column
    private String nickName;

    public User joinUser(UserDto.Req req) {
        this.nickName = req.getNickName();
        return this;
    }

}
