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
public class MovieAwardController {

    private final MovieAwardService movieService;
    private final UpdateMovieAwardFacade movieAwardFacade;

    @Autowired
    public MovieAwardController(MovieAwardService movieService, UpdateMovieAwardFacade movieAwardFacade) {
        this.movieService = movieService;
        this.movieAwardFacade = movieAwardFacade;
    }

    @GetMapping("/short-info-list")
    public Page<MovieAwardShortViewProjection> getShortMovieInfoList(@RequestParam(name = "pageNumber", defaultValue = "0") int pageNumber,
                                                                 @RequestParam(name = "pageSize", defaultValue = "10") int pageSize,
                                                                 @RequestParam(name = "sortDirection", defaultValue = "DESC") Sort.Direction sortDirection,
                                                                 @RequestParam(name = "sortFields", defaultValue = "boxOffice") String[] sortFields) {
        return movieService.getMoviesShortInfoList(pageNumber, pageSize, sortDirection, sortFields);
    }

    @PostMapping("/update/from-omdb")
    public ResponseEntity updateMovieAwardsFromImdb() {
        movieAwardFacade.updateMovieAwardsFromImdb();

        return ResponseEntity.ok().build();
    }
}
