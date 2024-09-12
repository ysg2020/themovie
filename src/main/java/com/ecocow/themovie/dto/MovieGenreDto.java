package com.ecocow.themovie.dto;

import com.ecocow.themovie.entity.Genre;
import com.ecocow.themovie.entity.Movie;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieGenreDto {

    private Long genreId;
    private String genreName;

    public static MovieGenreDto fromEntity(Genre genre) {
        return MovieGenreDto.builder()
                .genreId(genre.getGenreId())
                .genreName(genre.getGenreName())
                .build();
    }

}
