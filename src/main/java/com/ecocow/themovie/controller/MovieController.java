package com.ecocow.themovie.controller;

import com.ecocow.themovie.dto.CreditsDto;
import com.ecocow.themovie.dto.MovieDetailsDto;
import com.ecocow.themovie.dto.MovieResultDto;
import com.ecocow.themovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    private MovieDetailsDto getMovie(@RequestParam Long movieId, @RequestParam String language) throws IOException, InterruptedException {
        return movieService.getMovie(movieId,language);
    }

    @GetMapping("/recommend")
    private MovieResultDto getRecommendMovie(@RequestParam Long movieId, @RequestParam String language, @RequestParam int page) throws IOException, InterruptedException {
        return movieService.getRecommendMovie(movieId,language,page);
    }

    @GetMapping("/popular")
    private MovieResultDto getPopularMovie(@RequestParam String language, @RequestParam int page) throws IOException, InterruptedException {
        return movieService.getPopularMovie(language,page);
    }

    @GetMapping("/cast")
    private CreditsDto getCast(@RequestParam Long movieId, @RequestParam String language) throws IOException, InterruptedException {
        return movieService.getCast(movieId,language);
    }



}
