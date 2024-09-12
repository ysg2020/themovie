package com.ecocow.themovie.service.impl;

import com.ecocow.themovie.dto.MovieDetailsDto;
import com.ecocow.themovie.dto.MovieDto;
import com.ecocow.themovie.entity.Movie;
import com.ecocow.themovie.repository.MovieRepository;
import com.ecocow.themovie.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public MovieDetailsDto getMovie(Long movieId) {
        // 영화 상세 조회
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException());

        // 엔티티에서 DTO로 변환 후 리턴
        return MovieDetailsDto.fromEntity(movie);
    }

    @Override
    public List<MovieDto> getRecommendMovie(Long movieId) {
        // 장르가 2개 이상 겹치는 영화 조회
        List<Movie> moviesWithMatchingGenres = movieRepository.findMoviesWithMatchingGenresExcluding(movieId, movieId,2L);

        // 엔티티에서 DTO로 변환 후 리턴
        return moviesWithMatchingGenres.stream().map(MovieDto::fromEntity).toList();
    }
}
