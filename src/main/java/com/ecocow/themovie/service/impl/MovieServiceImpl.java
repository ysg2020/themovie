package com.ecocow.themovie.service.impl;

import com.ecocow.themovie.dto.CreditsDto;
import com.ecocow.themovie.dto.MovieDetailsDto;
import com.ecocow.themovie.dto.MovieResultDto;
import com.ecocow.themovie.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final ObjectMapper objectMapper;

    @Value("${the-movie-api-key}")
    private String theMovieApiKey;

    private String theMovieApiUrl = "https://api.themoviedb.org/3/movie/";

    @Override
    public MovieDetailsDto getMovie(Long movieId, String language) throws IOException, InterruptedException {
        String uri = theMovieApiUrl + movieId + "?language=" + language;
        return getTheMovieAPI(uri, MovieDetailsDto.class);

    }


    @Override
    public MovieResultDto getRecommendMovie(Long movieId, String language, int page) throws IOException, InterruptedException {
        String uri = theMovieApiUrl + movieId + "/recommendations?language=" + language + "&page=" + page;
        return getTheMovieAPI(uri,MovieResultDto.class);

    }

    @Override
    public MovieResultDto getPopularMovie(String language, int page) throws IOException, InterruptedException {
        String uri = theMovieApiUrl + "popular?language=" + language + "&page=" + page;
        return getTheMovieAPI(uri,MovieResultDto.class);

    }


    @Override
    public CreditsDto getCast(Long movieId, String language) throws IOException, InterruptedException {
        String uri = theMovieApiUrl + movieId + "/credits?language=" + language;
        return getTheMovieAPI(uri,CreditsDto.class);

    }

    private <T> T getTheMovieAPI(String uri,Class<T> responseType) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("accept", "application/json")
                .header("Authorization", theMovieApiKey)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), responseType);

    }


}
