package com.movieflix.controller;

import com.movieflix.controller.request.MovieRequest;
import com.movieflix.controller.response.MovieResponse;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @PostMapping
    public ResponseEntity<MovieResponse>save(@Valid @RequestBody MovieRequest movie){
       Movie saved = movieService.save(MovieMapper.toMovie(movie));
       return ResponseEntity.ok(MovieMapper.toMovieResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> listAll(){
        return ResponseEntity.ok(movieService.getAll().stream()
                .map(movie -> MovieMapper.toMovieResponse(movie)).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id){
      return movieService.getById(id)
                .map(movie ->ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
              .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieResponse> update(@PathVariable Long id, @Valid @RequestBody MovieRequest request){
    return movieService.update(id,MovieMapper.toMovie(request))
            .map(movie -> ResponseEntity.ok(MovieMapper.toMovieResponse(movie)))
            .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/search")
    public ResponseEntity<List<MovieResponse>> findByCategory(@RequestParam Long category){
       return ResponseEntity.ok(movieService.findByCategory(category).stream()
               .map(movie -> MovieMapper.toMovieResponse(movie)).toList());
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteById(@RequestParam Long id){
     movieService.deleteById(id);
     return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
