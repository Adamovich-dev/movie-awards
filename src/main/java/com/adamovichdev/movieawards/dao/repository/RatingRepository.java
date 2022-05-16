package com.adamovichdev.movieawards.dao.repository;

import com.adamovichdev.movieawards.dao.entity.RatingEntity;
import com.adamovichdev.movieawards.dao.entity.RatingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<RatingEntity, RatingId> {

}
