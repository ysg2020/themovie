package com.ecocow.themovie.service;

import com.ecocow.themovie.dto.CreditsDto;
import com.ecocow.themovie.dto.MovieDetailsDto;
import com.ecocow.themovie.dto.MovieResultDto;
import com.ecocow.themovie.dto.SearchMovieDto;

import java.io.IOException;

public interface MovieService {

     MovieDetailsDto getMovie(Long movieId, String language, String append_to_response) throws IOException, InterruptedException;

     MovieResultDto getRecommendMovie(Long movieId, String language, int page) throws IOException, InterruptedException;

     MovieResultDto getPopularMovie(String language, int page, String region) throws IOException, InterruptedException;

     CreditsDto getCast(Long movieId, String language) throws IOException, InterruptedException;

     MovieResultDto getTrendingAll(String timeWindow, String language) throws IOException, InterruptedException;

     MovieResultDto getSearchMovie(SearchMovieDto searchMovieDto) throws IOException, InterruptedException;
}
