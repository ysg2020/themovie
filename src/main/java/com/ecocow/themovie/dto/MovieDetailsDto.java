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
public class MovieDetailsDto {

    private Long movieId;                                               // 식별자
    private String title;                                               // 제목
    private String originalTitle;                                       // 원제
    private String originalLanguage;                                    // 원어
    private List<MovieGenreDto> genres;                                 // 장르
    private String tagline;                                             // 간단소개
    private String overview;                                            // 개요
    private String posterPath;                                          // 포스터 이미지
    private List<MovieProductionCountryDto> productionCountries;        // 제작 국가
    private List<MovieCastDto> casts;                                   // 출연진

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;                                      // 개봉일

    private boolean status;                                             // 개봉 여부
    private int runtime;                                                // 상영시간

    public static MovieDetailsDto fromEntity(Movie movie) {
        return MovieDetailsDto.builder()
                .movieId(movie.getMovieId())
                .title(movie.getTitle())
                .originalTitle(movie.getOriginalTitle())
                .originalLanguage(movie.getOriginalLanguage())
                .genres(movie.getGenres().stream().map(MovieGenreDto::fromEntity).toList())
                .tagline(movie.getTagline())
                .overview(movie.getOverview())
                .posterPath(movie.getPosterPath())
                .productionCountries(movie.getProductionCountries().stream().map(MovieProductionCountryDto::fromEntity).toList())
                .casts(movie.getCasts().stream().map(MovieCastDto::fromEntity).toList())
                .releaseDate(movie.getReleaseDate())
                .status(movie.isStatus())
                .runtime(movie.getRuntime())
                .build();
    }


}
