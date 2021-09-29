package com.toy.pbuser.bird.repository;

import com.toy.pbuser.bird.domain.Bird;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BirdRepository extends JpaRepository<Bird, Long> {

    Long countByBirdName(String birdName);

}
