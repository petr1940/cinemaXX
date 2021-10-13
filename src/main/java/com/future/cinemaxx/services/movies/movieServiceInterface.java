package com.future.cinemaxx.services.movies;

import com.future.cinemaxx.entities.Movie;

import java.util.List;

public interface movieServiceInterface {
    List<Movie> getAllMovies();
    Movie getMovieById(int id);
    Movie createMovie(Movie movie, int genreId, int categoryId);
    void deleteMovie(int movieId);
}