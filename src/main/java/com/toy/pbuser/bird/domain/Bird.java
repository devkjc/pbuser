package com.toy.pbuser.bird.domain;

import com.toy.pbuser.common.domain.BaseTimeEntity;
import com.toy.pbuser.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "bird")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bird extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(name = "uid_fk")
    private String uid;

    private String birdName;

    public void setBirdName(String birdName) {
        this.birdName = birdName;
    }
}
