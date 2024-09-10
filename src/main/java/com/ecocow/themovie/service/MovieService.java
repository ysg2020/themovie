package com.ecocow.themovie.service;

import com.ecocow.themovie.dto.CreditsDto;
import com.ecocow.themovie.dto.MovieDetailsDto;
import com.ecocow.themovie.dto.MovieResultDto;

import java.io.IOException;

public interface MovieService {

     MovieDetailsDto getMovie(Long movieId, String language) throws IOException, InterruptedException;
     MovieResultDto getRecommendMovie(Long movieId, String language, int page) throws IOException, InterruptedException;
     MovieResultDto getPopularMovie(String language, int page) throws IOException, InterruptedException;
     CreditsDto getCast(Long movieId, String language) throws IOException, InterruptedException;

}
