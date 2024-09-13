package com.ecocow.themovie.service.impl;

import com.ecocow.themovie.dto.TheMovieCreditsDto;
import com.ecocow.themovie.dto.TheMovieDetailsDto;
import com.ecocow.themovie.dto.TheMovieResultDto;
import com.ecocow.themovie.dto.TheMovieSearchDto;
import com.ecocow.themovie.service.TheMovieService;
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
public class TheMovieServiceImpl implements TheMovieService {

    private final ObjectMapper objectMapper;

    @Value("${the-movie-api-key}")
    private String theMovieApiKey;

    private String movieUrl = "https://api.themoviedb.org/3/movie/";
    private String trendingAllUrl = "https://api.themoviedb.org/3/trending/all/";
    private String searchMovieUrl = "https://api.themoviedb.org/3/search/movie";

    @Override
    public TheMovieDetailsDto getMovie(Long movieId, String language, String append_to_response) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(movieUrl)
                .path(String.valueOf(movieId))
                .queryParam("language", language);

        if (append_to_response != null) {
            uriBuilder.queryParam("append_to_response",append_to_response);
        }

        return getTheMovieAPI(uriBuilder.toUriString(), TheMovieDetailsDto.class);

    }

    @Override
    public TheMovieResultDto getRecommendMovie(Long movieId, String language, int page) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(movieUrl)
                .path(String.valueOf(movieId))
                .path("/recommendations")
                .queryParam("language", language)
                .queryParam("page", page);
        return getTheMovieAPI(uriBuilder.toUriString(), TheMovieResultDto.class);

    }

    @Override
    public TheMovieResultDto getPopularMovie(String language, int page, String region) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(movieUrl)
                .path("/popular")
                .queryParam("language", language)
                .queryParam("page", page);

        if (region != null) {
            uriBuilder.queryParam("region",region);
        }

        return getTheMovieAPI(uriBuilder.toUriString(), TheMovieResultDto.class);

    }

    @Override
    public TheMovieResultDto getUpcomingMovie(String language, int page, String region) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(movieUrl)
                .path("/upcoming")
                .queryParam("language", language)
                .queryParam("page", page);

        if (region != null) {
            uriBuilder.queryParam("region",region);
        }

        return getTheMovieAPI(uriBuilder.toUriString(), TheMovieResultDto.class);
    }


    @Override
    public TheMovieCreditsDto getCast(Long movieId, String language) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(movieUrl)
                .path(String.valueOf(movieId))
                .path("/credits")
                .queryParam("language", language);

        return getTheMovieAPI(uriBuilder.toUriString(), TheMovieCreditsDto.class);

    }

    @Override
    public TheMovieResultDto getTrendingAll(String timeWindow, String language) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(trendingAllUrl)
                .path(timeWindow)
                .queryParam("language", language);

        return getTheMovieAPI(uriBuilder.toUriString(), TheMovieResultDto.class);
    }

    @Override
    public TheMovieResultDto getSearchMovie(TheMovieSearchDto theMovieSearchDto) throws IOException, InterruptedException {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(searchMovieUrl)
                .queryParam("query", theMovieSearchDto.getQuery())
                .queryParam("include_adult", theMovieSearchDto.isInclude_adult())
                .queryParam("language", theMovieSearchDto.getLanguage())
                .queryParam("page", theMovieSearchDto.getPage());

        if (!theMovieSearchDto.getPrimary_release_year().isEmpty()) {
            uriBuilder.queryParam("primary_release_year", theMovieSearchDto.getPrimary_release_year());
        }

        if (!theMovieSearchDto.getRegion().isEmpty()) {
            uriBuilder.queryParam("region", theMovieSearchDto.getRegion());
        }

        if (!theMovieSearchDto.getYear().isEmpty()) {
            uriBuilder.queryParam("year", theMovieSearchDto.getYear());
        }

        return getTheMovieAPI(uriBuilder.toUriString(), TheMovieResultDto.class);
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
