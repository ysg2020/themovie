package com.ecocow.themovie.controller;

import com.ecocow.themovie.dto.MovieDetailsDto;
import com.ecocow.themovie.dto.MovieDto;
import com.ecocow.themovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MoviceController {


    private final MovieService movieService;

    @GetMapping
    private MovieDetailsDto getMovie(@RequestParam Long movieId) throws IOException, InterruptedException {
        return movieService.getMovie(movieId);
    }

    @GetMapping("/recommend")
    private List<MovieDto> getRecommendMovie(@RequestParam Long movieId) throws IOException, InterruptedException {
        return movieService.getRecommendMovie(movieId);
    }
}
