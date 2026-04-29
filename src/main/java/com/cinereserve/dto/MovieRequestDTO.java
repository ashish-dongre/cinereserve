package com.cinereserve.dto;

import com.cinereserve.enums.Genre;
import com.cinereserve.enums.Language;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class MovieRequestDTO {

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 minute")
    private Integer duration;

    private Double rating;

    private LocalDate releaseDate;

    @NotNull(message = "Genre is required")
    private Genre genre;

    @NotNull(message = "Language is required")
    private Language language;
}