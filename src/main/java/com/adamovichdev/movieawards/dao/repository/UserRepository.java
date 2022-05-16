package com.adamovichdev.movieawards.dao.repository;

import com.adamovichdev.movieawards.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {
    UserEntity findByUserName(String userName);
}
