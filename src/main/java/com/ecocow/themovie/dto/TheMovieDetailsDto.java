package com.ecocow.themovie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TheMovieDetailsDto {

    private Long id;                                                // 식별자
    private String title;                                           // 제목
    private String original_title;                                  // 원제
    private String original_language;                               // 원어
    private TheMovieGenreDto[] genres;                              // 장르
    private String tagline;                                         // 간단소개
    private String overview;                                        // 개요
    private String poster_path;                                     // 포스터 이미지
    private TheMovieProductionCountryDto[] production_countries;    // 제작 국가
    private String release_date;                                    // 개봉일
    private String status;                                          // 개봉 여부
    private String runtime;                                         // 상영시간

}
