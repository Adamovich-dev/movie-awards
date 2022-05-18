package com.adamovichdev.movieawards.web.controler;

import com.adamovichdev.movieawards.dao.entity.projection.MovieAwardShortViewProjection;
import com.adamovichdev.movieawards.service.facade.UpdateMovieAwardFacade;
import com.adamovichdev.movieawards.service.movie.MovieAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/movie-award")
public class MovieController {

    private final MovieAwardService movieService;
    private final UpdateMovieAwardFacade movieAwardFacade;

    @Autowired
    public MovieController(MovieAwardService movieService, UpdateMovieAwardFacade movieAwardFacade) {
        this.movieService = movieService;
        this.movieAwardFacade = movieAwardFacade;
    }

    @GetMapping("/short-info-list")
    public Page<MovieAwardShortViewProjection> getShortMovieInfo(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                 @RequestParam(name = "size", defaultValue = "10") int size,
                                                                 @RequestParam(name = "direction", defaultValue = "DESC") Sort.Direction direction,
                                                                 @RequestParam(name = "sortFields", defaultValue = "boxOffice") String[] sortFields) {
        return movieService.getAllMoviesShortInfo(page, size, direction, sortFields);
    }

    @PostMapping("/update/from-omdb")
    public ResponseEntity getAllMovieTitles() {
        movieAwardFacade.updateMovieAwardsFromImdb();

        return ResponseEntity.ok().build();
    }
}
