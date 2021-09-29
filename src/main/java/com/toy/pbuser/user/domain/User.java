package com.toy.pbuser.user.domain;

import com.toy.pbuser.bird.domain.Bird;
import com.toy.pbuser.bird.dto.BirdDto;
import com.toy.pbuser.common.domain.BaseTimeEntity;
import com.toy.pbuser.postbox.domain.PostBox;
import com.toy.pbuser.postbox.dto.PostBoxDto;
import com.toy.pbuser.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "user")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseTimeEntity {

    @Id
    private String uid;

    @Column
    private String nickName;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Bird> birdList;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PostBox> postBoxList;

    public void setNickname(String nickName) {
        this.nickName = nickName;
    }

    public UserDto.SimpleRes getSimpleUser() {
        return UserDto.SimpleRes.of(this);
    }

    public List<BirdDto.Res> getBirdResList() {
        return birdList.stream().map(BirdDto.Res::of).collect(Collectors.toList());
    }
    public List<BirdDto.SimpleRes> getBirdSimpleResList() {
        return birdList.stream().map(BirdDto.SimpleRes::of).collect(Collectors.toList());
    }

    public List<PostBoxDto.Res> getPostBoxResList() {
        return postBoxList.stream().map(PostBoxDto.Res::of).collect(Collectors.toList());
    }
    public List<PostBoxDto.SimpleRes> getPostBoxSimpleResList() {
        return postBoxList.stream().map(PostBoxDto.SimpleRes::of).collect(Collectors.toList());
    }
}
