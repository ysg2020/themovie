package com.ecocow.themovie.dto;

import com.ecocow.themovie.entity.Cast;
import com.ecocow.themovie.entity.Genre;
import com.ecocow.themovie.entity.Movie;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieCastDto {

    private Long castId;
    private String castName;
    private String characterName;
    private String profilePath;

    public static MovieCastDto fromEntity(Cast cast) {
        return MovieCastDto.builder()
                .castId(cast.getCastId())
                .castName(cast.getCastName())
                .characterName(cast.getCharacterName())
                .profilePath(cast.getProfilePath())
                .build();
    }

}
