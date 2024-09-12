package com.ecocow.themovie.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductionCountry {

    @Id
    @Column(name = "production_country_id")
    private Long productionCountryId;       // 제작 국가 식별자

    @Column(name = "country_code")
    private String countryCode;             // 국가 코드

    @Column(name = "country_name")
    private String countryName;             // 국가 이름


}
