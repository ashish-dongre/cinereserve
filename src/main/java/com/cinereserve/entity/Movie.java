package com.cinereserve.entity;

import com.cinereserve.enums.Genre;
import com.cinereserve.enums.Language;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private Integer duration; // in minutes

    private Double rating;

    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToMany(mappedBy = "movie")
    private List<Show> shows;
}