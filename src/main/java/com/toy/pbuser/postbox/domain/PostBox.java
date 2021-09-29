package com.toy.pbuser.postbox.domain;

import com.toy.pbuser.common.domain.BaseTimeEntity;
import com.toy.pbuser.common.domain.Address;
import com.toy.pbuser.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "post_box")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostBox extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "uid_fk")
    private User user;

    @Embedded
    private Address address;

}
