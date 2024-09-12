package com.ecocow.themovie.service;

import com.ecocow.themovie.dto.MovieDetailsDto;
import com.ecocow.themovie.dto.MovieDto;

import java.util.List;

public interface MovieService {

    MovieDetailsDto getMovie(Long movieId);

    List<MovieDto> getRecommendMovie(Long movieId);
}
