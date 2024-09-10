package com.ecocow.themovie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RecommendMovieDto {

    private Long id;                                        // 식별자
    private String title;                                   // 제목
    private String original_title;                          // 원제
    private String original_language;                       // 원어
    private Long[] genre_ids;                               // 장르
    private String overview;                                // 개요
    private String poster_path;                             // 포스터 이미지
    private String release_date;                            // 개봉일

}
