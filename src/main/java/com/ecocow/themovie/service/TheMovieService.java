package com.ecocow.themovie.service;

import com.ecocow.themovie.dto.TheMovieCreditsDto;
import com.ecocow.themovie.dto.TheMovieDetailsDto;
import com.ecocow.themovie.dto.TheMovieResultDto;
import com.ecocow.themovie.dto.TheMovieSearchDto;

import java.io.IOException;

public interface TheMovieService {

     TheMovieDetailsDto getMovie(Long movieId, String language, String append_to_response) throws IOException, InterruptedException;

     TheMovieResultDto getRecommendMovie(Long movieId, String language, int page) throws IOException, InterruptedException;

     TheMovieResultDto getPopularMovie(String language, int page, String region) throws IOException, InterruptedException;

     TheMovieCreditsDto getCast(Long movieId, String language) throws IOException, InterruptedException;

     TheMovieResultDto getTrendingAll(String timeWindow, String language) throws IOException, InterruptedException;

     TheMovieResultDto getSearchMovie(TheMovieSearchDto theMovieSearchDto) throws IOException, InterruptedException;

     TheMovieResultDto getUpcomingMovie(String language, int page, String region) throws IOException, InterruptedException;
}
