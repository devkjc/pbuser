//package com.toy.pbuser.user.service;
//
//import com.toy.pbuser.config.security.SecurityService;
//import com.toy.pbuser.user.domain.User;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class UserService {
//
//    public static User getAuthUser() {
//        return SecurityService.getUser();
//    }
//
//    public static String getAuthUid() {
//        User authUser = getAuthUser();
//        return authUser.getUid();
//    }
//}
