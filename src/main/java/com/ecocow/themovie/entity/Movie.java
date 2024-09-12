package com.ecocow.themovie.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Movie {

    @Id
    @Column(name = "movie_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieId;                   // 영화 식별자

    private String title;                   // 제목

    @Column(name = "original_title")
    private String originalTitle;           // 원제

    @Column(name = "original_language")
    private String originalLanguage;        // 원어

    private String tagline;                 // 간단소개
    private String overview;                // 개요

    @Column(name = "poster_path")
    private String posterPath;              // 포스터 이미지

    @Column(name = "release_date")
    private LocalDate releaseDate;          // 개봉일

    private boolean status;                 // 개봉여부
    private int runtime;                    // 상영시간

    @ManyToMany
    @JoinTable(
            name = "movie_genre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns  = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres;

    @ManyToMany
    @JoinTable(
            name = "movie_cast",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns  = @JoinColumn(name = "cast_id")
    )
    private List<Cast> casts;

    @ManyToMany
    @JoinTable(
            name = "movie_productionCountry",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns  = @JoinColumn(name = "production_country_id")
    )
    private List<ProductionCountry> productionCountries;


}
