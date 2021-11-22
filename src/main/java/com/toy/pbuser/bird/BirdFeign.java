package com.toy.pbuser.bird;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;

@FeignClient("pbbird")
public interface BirdFeign {

    @DeleteMapping("/api/v1/bird")
    void deleteBird();

}
