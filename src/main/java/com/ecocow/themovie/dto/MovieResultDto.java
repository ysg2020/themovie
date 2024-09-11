package com.ecocow.themovie.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieResultDto {

    private int page;
    private MovieDto[] results;
    private int total_pages;
    private int total_results;
}
