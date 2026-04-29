package com.cinereserve.service;

import com.cinereserve.dto.ShowRequestDTO;
import com.cinereserve.entity.Show;
import com.cinereserve.repository.MovieRepository;
import com.cinereserve.repository.ScreenRepository;
import com.cinereserve.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;
    private final MovieRepository movieRepository;
    private final ScreenRepository screenRepository;

    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Show getShowById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + id));
    }

    public List<Show> getShowsByMovie(Long movieId) {
        return showRepository.findByMovieId(movieId);
    }

    public Show createShow(ShowRequestDTO request) {
        Show show = Show.builder()
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .movie(movieRepository.findById(request.getMovieId())
                        .orElseThrow(() -> new RuntimeException("Movie not found")))
                .screen(screenRepository.findById(request.getScreenId())
                        .orElseThrow(() -> new RuntimeException("Screen not found")))
                .build();

        return showRepository.save(show);
    }

    public Show updateShow(Long id, ShowRequestDTO request) {
        Show show = getShowById(id);
        show.setStartTime(request.getStartTime());
        show.setEndTime(request.getEndTime());
        show.setMovie(movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found")));
        show.setScreen(screenRepository.findById(request.getScreenId())
                .orElseThrow(() -> new RuntimeException("Screen not found")));

        return showRepository.save(show);
    }

    public void deleteShow(Long id) {
        showRepository.deleteById(id);
    }
}