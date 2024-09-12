package com.ecocow.themovie.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TheMovieCastDto {

    private Long id;                // 식별자
    private String name;            // 이름
    private String character;       // 캐릭터
    private String profile_path;    // 프로필 이미지

}
