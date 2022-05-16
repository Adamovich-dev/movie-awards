package com.adamovichdev.movieawards.dao.repository;

import com.adamovichdev.movieawards.dao.entity.MovieAwardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieAwardEntity,Integer> {
}
