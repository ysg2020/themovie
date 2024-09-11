package com.ecocow.themovie.controller;

import com.ecocow.themovie.dto.CreditsDto;
import com.ecocow.themovie.dto.MovieDetailsDto;
import com.ecocow.themovie.dto.MovieResultDto;
import com.ecocow.themovie.dto.SearchMovieDto;
import com.ecocow.themovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    private MovieDetailsDto getMovie(@RequestParam Long movieId, @RequestParam String language, @RequestParam(required = false) String append_to_response) throws IOException, InterruptedException {
        return movieService.getMovie(movieId,language,append_to_response);
    }

    @GetMapping("/recommend")
    private MovieResultDto getRecommendMovie(@RequestParam Long movieId, @RequestParam String language, @RequestParam int page) throws IOException, InterruptedException {
        return movieService.getRecommendMovie(movieId,language,page);
    }

    @GetMapping("/popular")
    private MovieResultDto getPopularMovie(@RequestParam String language, @RequestParam int page, @RequestParam(required = false) String region) throws IOException, InterruptedException {
        return movieService.getPopularMovie(language,page,region);
    }

    @GetMapping("/cast")
    private CreditsDto getCast(@RequestParam Long movieId, @RequestParam String language) throws IOException, InterruptedException {
        return movieService.getCast(movieId,language);
    }

    @GetMapping("/trending/All")
    private MovieResultDto getTrending(@RequestParam String timeWindow, @RequestParam String language) throws IOException, InterruptedException {
        return movieService.getTrendingAll(timeWindow,language);
    }

    @GetMapping("/search")
    private MovieResultDto getSearchMovie(@RequestBody SearchMovieDto searchMovieDto) throws IOException, InterruptedException {
        return movieService.getSearchMovie(searchMovieDto);
    }


}
