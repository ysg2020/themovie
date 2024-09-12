package com.ecocow.themovie.dto;

import com.ecocow.themovie.entity.Movie;
import com.ecocow.themovie.entity.ProductionCountry;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieProductionCountryDto {

    private Long productionCountryId;
    private String countryCode;
    private String countryName;

    public static MovieProductionCountryDto fromEntity(ProductionCountry productionCountry) {
        return MovieProductionCountryDto.builder()
                .productionCountryId(productionCountry.getProductionCountryId())
                .countryCode(productionCountry.getCountryCode())
                .countryName(productionCountry.getCountryName())
                .build();
    }

}
