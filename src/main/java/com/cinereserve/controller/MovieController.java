package com.cinereserve.controller;

import com.cinereserve.dto.MovieRequestDTO;
import com.cinereserve.entity.Movie;
import com.cinereserve.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public Movie createMovie(@Valid @RequestBody MovieRequestDTO request) {
        return movieService.createMovie(request);
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @Valid @RequestBody MovieRequestDTO request) {
        return movieService.updateMovie(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }
}