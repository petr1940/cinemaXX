package com.future.cinemaxx.services.genres;

import com.future.cinemaxx.entities.Genre;
import com.future.cinemaxx.repositories.GenreRepo;
import com.future.cinemaxx.repositories.MovieRepo;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class GenreServiceImpl implements GenreServiceInterface {
    GenreRepo genreRepo;
    MovieRepo movieRepo;

    public GenreServiceImpl(GenreRepo genreRepo, MovieRepo movieRepo) {
        this.genreRepo = genreRepo;
        this.movieRepo = movieRepo;
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepo.findAll();
    }

    @Override
    public Genre getGenreById(int id) {
        return genreRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("There is not such Genre"));
    }

    @Override
    public Genre createGenre(Genre genre) {
        if (genreRepo.existsByName(genre.getName())) {
            throw new IllegalArgumentException("A genre with name: " + genre.getName() + " already exists");
        }
        return genreRepo.save(genre);
    }

    @Override
    public Genre updateGenre(int genreId, Genre genre) {
        Genre updatedGenre = genreRepo.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("There is not such Genre"));
        if (genre.getName() != null) {
            updatedGenre.setName(genre.getName());
        }
        return genreRepo.save(updatedGenre);
    }

    @Override
    public void deleteGenre(int genreId) {
        if (!movieRepo.existsByGenre(genreRepo.findById(genreId).orElseThrow(() -> new ResourceNotFoundException("There is not such genre in our system."))))
            genreRepo.deleteById(genreId);
        else {
            throw new IllegalStateException("There are movies using this genre. You need to delete them first!");
        }
    }
}
