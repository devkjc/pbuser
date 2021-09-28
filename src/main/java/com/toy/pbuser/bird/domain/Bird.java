package com.toy.pbuser.bird.domain;

import com.toy.pbuser.common.domain.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Bird extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    private String birdName;

}
