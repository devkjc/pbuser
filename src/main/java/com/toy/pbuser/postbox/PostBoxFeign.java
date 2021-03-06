package com.toy.pbuser.postbox;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient("pbpost")
public interface PostBoxFeign {

    @DeleteMapping("/api/v1/postbox")
    void deletePostBox();

    @GetMapping("/api/v1/postbox")
    List<Object> getPostBox();

    @GetMapping("/api/v1/addressBook")
    List<Object> getAddressBook();
}
