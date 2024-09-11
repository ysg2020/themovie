package com.ecocow.themovie.service.impl;

import com.ecocow.themovie.dto.CreditsDto;
import com.ecocow.themovie.dto.MovieDetailsDto;
import com.ecocow.themovie.dto.MovieResultDto;
import com.ecocow.themovie.dto.SearchMovieDto;
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
    private String searchMovieUrl = "https://api.themoviedb.org/3/search/movie/";

    @Override
    public MovieDetailsDto getMovie(Long movieId, String language, String append_to_response) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(movieUrl)
                .path(String.valueOf(movieId))
                .queryParam("language", language);

        if (append_to_response != null) {
            uriBuilder.queryParam("append_to_response",append_to_response);
        }

        return getTheMovieAPI(uriBuilder.toUriString(), MovieDetailsDto.class);

    }

    @Override
    public MovieResultDto getRecommendMovie(Long movieId, String language, int page) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(movieUrl)
                .path(String.valueOf(movieId))
                .path("/recommendations")
                .queryParam("language", language)
                .queryParam("page", page);
        return getTheMovieAPI(uriBuilder.toUriString(),MovieResultDto.class);

    }

    @Override
    public MovieResultDto getPopularMovie(String language, int page, String region) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(movieUrl)
                .path("/popular")
                .queryParam("language", language)
                .queryParam("page", page);

        if (region != null) {
            uriBuilder.queryParam("region",region);
        }

        return getTheMovieAPI(uriBuilder.toUriString(),MovieResultDto.class);

    }

    @Override
    public MovieResultDto getUpcomingMovie(String language, int page, String region) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(movieUrl)
                .path("/upcoming")
                .queryParam("language", language)
                .queryParam("page", page);

        if (region != null) {
            uriBuilder.queryParam("region",region);
        }

        return getTheMovieAPI(uriBuilder.toUriString(),MovieResultDto.class);
    }


    @Override
    public CreditsDto getCast(Long movieId, String language) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(movieUrl)
                .path(String.valueOf(movieId))
                .path("/credits")
                .queryParam("language", language);

        return getTheMovieAPI(uriBuilder.toUriString(),CreditsDto.class);

    }

    @Override
    public MovieResultDto getTrendingAll(String timeWindow, String language) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(trendingAllUrl)
                .path(timeWindow)
                .queryParam("language", language);

        return getTheMovieAPI(uriBuilder.toUriString(),MovieResultDto.class);
    }

    @Override
    public MovieResultDto getSearchMovie(SearchMovieDto searchMovieDto) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(searchMovieUrl)
                .queryParam("query", searchMovieDto.getQuery())
                .queryParam("include_adult", searchMovieDto.isInclude_adult())
                .queryParam("language", searchMovieDto.getLanguage())
                .queryParam("page", searchMovieDto.getPage());

        if (!searchMovieDto.getPrimary_release_year().isEmpty()) {
            uriBuilder.queryParam("primary_release_year", searchMovieDto.getPrimary_release_year());
        }

        if (!searchMovieDto.getRegion().isEmpty()) {
            uriBuilder.queryParam("region", searchMovieDto.getRegion());
        }

        if (!searchMovieDto.getYear().isEmpty()) {
            uriBuilder.queryParam("year", searchMovieDto.getYear());
        }

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
