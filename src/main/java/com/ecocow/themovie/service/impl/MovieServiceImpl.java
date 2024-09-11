package com.ecocow.themovie.service.impl;

import com.ecocow.themovie.dto.CreditsDto;
import com.ecocow.themovie.dto.MovieDetailsDto;
import com.ecocow.themovie.dto.MovieResultDto;
import com.ecocow.themovie.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

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

    private String movieUrl = "https://api.themoviedb.org/3/movie/";
    private String trendingAllUrl = "https://api.themoviedb.org/3/trending/all/";
    private String searchMovieUrl = "https://api.themoviedb.org/3/search/movie";

    @Override
    public MovieDetailsDto getMovie(Long movieId, String language) throws IOException, InterruptedException {
        String uri = movieUrl + movieId + "?language=" + language;
        return getTheMovieAPI(uri, MovieDetailsDto.class);

    }


    @Override
    public MovieResultDto getRecommendMovie(Long movieId, String language, int page) throws IOException, InterruptedException {
        String uri = movieUrl + movieId + "/recommendations?language=" + language + "&page=" + page;
        return getTheMovieAPI(uri,MovieResultDto.class);

    }

    @Override
    public MovieResultDto getPopularMovie(String language, int page) throws IOException, InterruptedException {
        String uri = movieUrl + "popular?language=" + language + "&page=" + page;
        return getTheMovieAPI(uri,MovieResultDto.class);

    }


    @Override
    public CreditsDto getCast(Long movieId, String language) throws IOException, InterruptedException {
        String uri = movieUrl + movieId + "/credits?language=" + language;
        return getTheMovieAPI(uri,CreditsDto.class);

    }

    @Override
    public MovieResultDto getTrendingAll(String timeWindow, String language) throws IOException, InterruptedException {
        String uri = trendingAllUrl + timeWindow + "?language=" + language;
        return getTheMovieAPI(uri,MovieResultDto.class);
    }

    @Override
    public MovieResultDto getSearchMovie(String query, boolean includeAdult, String language, int page) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(searchMovieUrl)
                .queryParam("query", query)
                .queryParam("include_adult", includeAdult)
                .queryParam("language", language)
                .queryParam("page", page);

        return getTheMovieAPI(uriBuilder.toUriString(),MovieResultDto.class);
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
