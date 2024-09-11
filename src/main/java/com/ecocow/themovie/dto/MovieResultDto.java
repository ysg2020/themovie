package com.ecocow.themovie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResultDto {

    private int page;
    private MovieDto[] results;
    private int total_pages;
    private int total_results;
}
