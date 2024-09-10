package com.ecocow.themovie.service;

import com.ecocow.themovie.dto.MovieDto;
import com.ecocow.themovie.dto.RecommendResultDto;

import java.io.IOException;

public interface MovieService {

     MovieDto getMovie(Long movieId,String language) throws IOException, InterruptedException;
     RecommendResultDto getRecommendMovie(Long movieId, String language, int page) throws IOException, InterruptedException;

}
