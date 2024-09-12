package com.ecocow.themovie.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TheMovieSearchDto {

    private String query;
    private boolean include_adult;
    private String language;
    private String primary_release_year;
    private int page;
    private String region;
    private String year;

}
