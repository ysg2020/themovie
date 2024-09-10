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

    @Override
    public MovieDetailsDto getMovie(Long movieId, String language) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/"+movieId+"?language="+language))
                .header("accept", "application/json")
                .header("Authorization", theMovieApiKey)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        return objectMapper.readValue(response.body(), MovieDetailsDto.class);

    }

    @Override
    public MovieResultDto getRecommendMovie(Long movieId, String language, int page) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/"+movieId+"/recommendations?language="+language+"&page="+page))
                .header("accept", "application/json")
                .header("Authorization", theMovieApiKey)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        return objectMapper.readValue(response.body(), MovieResultDto.class);

    }

    @Override
    public MovieResultDto getPopularMovie(String language, int page) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/popular?language="+language+"&page="+page))
                .header("accept", "application/json")
                .header("Authorization", theMovieApiKey)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        return objectMapper.readValue(response.body(), MovieResultDto.class);

    }


    @Override
    public CreditsDto getCast(Long movieId, String language) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.themoviedb.org/3/movie/"+movieId+"/credits?language="+language))
                .header("accept", "application/json")
                .header("Authorization", theMovieApiKey)
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());

        return objectMapper.readValue(response.body(), CreditsDto.class);
    }
}
