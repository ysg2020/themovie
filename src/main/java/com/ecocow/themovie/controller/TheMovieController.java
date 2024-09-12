package com.ecocow.themovie.controller;

import com.ecocow.themovie.dto.TheMovieCreditsDto;
import com.ecocow.themovie.dto.TheMovieDetailsDto;
import com.ecocow.themovie.dto.TheMovieResultDto;
import com.ecocow.themovie.dto.TheMovieSearchDto;
import com.ecocow.themovie.service.TheMovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/the-movie")
@RequiredArgsConstructor
public class TheMovieController {

    private final TheMovieService theMovieService;

    @GetMapping
    private TheMovieDetailsDto getMovie(@RequestParam Long movieId, @RequestParam String language, @RequestParam(required = false) String append_to_response) throws IOException, InterruptedException {
        return theMovieService.getMovie(movieId,language,append_to_response);
    }

    @GetMapping("/recommend")
    private TheMovieResultDto getRecommendMovie(@RequestParam Long movieId, @RequestParam String language, @RequestParam int page) throws IOException, InterruptedException {
        return theMovieService.getRecommendMovie(movieId,language,page);
    }

    @GetMapping("/popular")
    private TheMovieResultDto getPopularMovie(@RequestParam String language, @RequestParam int page, @RequestParam(required = false) String region) throws IOException, InterruptedException {
        return theMovieService.getPopularMovie(language,page,region);
    }

    @GetMapping("/upcoming")
    private TheMovieResultDto getUpcomingMovie(@RequestParam String language, @RequestParam int page, @RequestParam(required = false) String region) throws IOException, InterruptedException {
        return theMovieService.getUpcomingMovie(language,page,region);
    }

    @GetMapping("/cast")
    private TheMovieCreditsDto getCast(@RequestParam Long movieId, @RequestParam String language) throws IOException, InterruptedException {
        return theMovieService.getCast(movieId,language);
    }

    @GetMapping("/trending/All")
    private TheMovieResultDto getTrending(@RequestParam String timeWindow, @RequestParam String language) throws IOException, InterruptedException {
        return theMovieService.getTrendingAll(timeWindow,language);
    }

    @GetMapping("/search")
    private TheMovieResultDto getSearchMovie(@RequestBody TheMovieSearchDto theMovieSearchDto) throws IOException, InterruptedException {
        return theMovieService.getSearchMovie(theMovieSearchDto);
    }


}
