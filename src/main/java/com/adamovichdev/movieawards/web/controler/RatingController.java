package com.adamovichdev.movieawards.web.controler;

import com.adamovichdev.movieawards.service.rating.RatingService;
import com.adamovichdev.movieawards.web.api.rating.RatingDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/api/rating")
public class RatingController {

    private final RatingService service;

    public RatingController(RatingService service) {
        this.service = service;
    }

    @GetMapping("{userId}/by-title")
    public RatingDto welcome(@PathVariable("userId") final Integer userId,
                             @RequestParam("titleId") final Integer titleId) {

        return service.getRatingByUserIdAndTitleId(userId, titleId);
    }

    @PostMapping("/save")
    public ResponseEntity saveRating(@RequestBody @NotNull RatingDto rating) {
        service.saveRating(rating);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/update")
    public ResponseEntity updateRating(@RequestBody @NotNull RatingDto rating) {
        service.updateRating(rating);

        return ResponseEntity.ok().build();
    }
}
