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
public class Cast {

    @Id
    @Column(name = "cast_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long castId;            // 출연진 식별자

    @Column(name = "cast_name")
    private String castName;        // 출연진 이름

    @Column(name = "character_name")
    private String characterName;   // 캐릭터 이름

    @Column(name = "profile_path")
    private String profilePath;     // 프로필 이미지
}
