package com.ecocow.themovie.repository;

import com.ecocow.themovie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query("SELECT m FROM Movie m " +
            "JOIN m.genres g " +
            "WHERE m.id <> :excludedMovieId " +
            "AND g IN (SELECT g2 FROM Movie m2 JOIN m2.genres g2 WHERE m2.id = :movieId) " +
            "GROUP BY m " +
            "HAVING COUNT(g) >= :minGenres")
    List<Movie> findMoviesWithMatchingGenresExcluding(@Param("movieId") Long movieId,
                                                      @Param("excludedMovieId") Long excludedMovieId,
                                                      @Param("minGenres") Long minGenres);


}
