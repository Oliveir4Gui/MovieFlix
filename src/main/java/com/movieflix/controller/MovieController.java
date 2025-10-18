package com.movieflix.controller;

import com.movieflix.controller.request.MovieRequest;
import com.movieflix.controller.response.MovieResponse;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse>save(@RequestBody MovieRequest movie){
       Movie saved = movieService.save(MovieMapper.toMovie(movie));
       return ResponseEntity.ok(MovieMapper.toMovieResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> listAll(){
        return ResponseEntity.ok(movieService.getAll().stream()
                .map(movie -> MovieMapper.toMovieResponse(movie)).toList());
    }
}
