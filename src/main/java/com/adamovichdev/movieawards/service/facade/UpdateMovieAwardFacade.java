package com.adamovichdev.movieawards.service.facade;

public interface UpdateMovieAwardFacade {

    /**
     * sends a request to the OMDB API for all movies in the database,
     * then gets the information (box office receipts, Imdb rating) and updates it in the database
     */
    void updateMovieAwardsFromImdb();
}
