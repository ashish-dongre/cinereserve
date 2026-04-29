package com.cinereserve.controller;

import com.cinereserve.dto.ShowRequestDTO;
import com.cinereserve.entity.Show;
import com.cinereserve.service.ShowService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shows")
@RequiredArgsConstructor
public class ShowController {

    private final ShowService showService;

    @GetMapping
    public List<Show> getAllShows() {
        return showService.getAllShows();
    }

    @GetMapping("/{id}")
    public Show getShowById(@PathVariable Long id) {
        return showService.getShowById(id);
    }

    @GetMapping("/movie/{movieId}")
    public List<Show> getShowsByMovie(@PathVariable Long movieId) {
        return showService.getShowsByMovie(movieId);
    }

    @PostMapping
    public Show createShow(@Valid @RequestBody ShowRequestDTO request) {
        return showService.createShow(request);
    }

    @PutMapping("/{id}")
    public Show updateShow(@PathVariable Long id, @Valid @RequestBody ShowRequestDTO request) {
        return showService.updateShow(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
    }
}