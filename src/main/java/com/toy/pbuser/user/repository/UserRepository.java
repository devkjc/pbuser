package com.toy.pbuser.user.repository;

import com.toy.pbuser.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    long countByNickName(String nickName);

}
