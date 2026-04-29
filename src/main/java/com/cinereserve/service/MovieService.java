package com.cinereserve.service;

import com.cinereserve.dto.MovieRequestDTO;
import com.cinereserve.entity.Movie;
import com.cinereserve.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
    }

    public Movie createMovie(MovieRequestDTO request) {
        Movie movie = Movie.builder()
                .title(request.getTitle())
                .duration(request.getDuration())
                .rating(request.getRating())
                .releaseDate(request.getReleaseDate())
                .genre(request.getGenre())
                .language(request.getLanguage())
                .build();

        return movieRepository.save(movie);
    }

    public Movie updateMovie(Long id, MovieRequestDTO request) {
        Movie movie = getMovieById(id);
        movie.setTitle(request.getTitle());
        movie.setDuration(request.getDuration());
        movie.setRating(request.getRating());
        movie.setReleaseDate(request.getReleaseDate());
        movie.setGenre(request.getGenre());
        movie.setLanguage(request.getLanguage());

        return movieRepository.save(movie);
    }

    public void deleteMovie(Long id) {
        movieRepository.deleteById(id);
    }
}