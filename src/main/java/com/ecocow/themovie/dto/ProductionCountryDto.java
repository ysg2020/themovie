package com.ecocow.themovie.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductionCountryDto {

    private String iso_3166_1;
    private String name;

}
