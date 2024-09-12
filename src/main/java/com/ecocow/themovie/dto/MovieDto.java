package com.ecocow.themovie.dto;

import com.ecocow.themovie.entity.Movie;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private Long movieId;                   // 식별자
    private String title;                   // 제목
    private String originalTitle;           // 원제
    private String originalLanguage;        // 원어
    private List<MovieGenreDto> genres;     // 장르
    private String overview;                // 개요
    private String posterPath;              // 포스터 이미지

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;          // 개봉일

    public static MovieDto fromEntity(Movie movie) {
        return MovieDto.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .originalTitle(movie.getOriginalTitle())
                .originalLanguage(movie.getOriginalLanguage())
                .genres(movie.getGenres().stream().map(MovieGenreDto::fromEntity).toList())
                .overview(movie.getOverview())
                .posterPath(movie.getPosterPath())
                .releaseDate(movie.getReleaseDate())
                .build();
    }

}
