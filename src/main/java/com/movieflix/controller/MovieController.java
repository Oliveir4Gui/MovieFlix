package com.movieflix.controller;

import com.movieflix.controller.request.MovieRequest;
import com.movieflix.controller.response.MovieResponse;
import com.movieflix.entity.Movie;
import com.movieflix.mapper.MovieMapper;
import com.movieflix.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movieflix/movie")
@RequiredArgsConstructor
@Tag(name = "Movie", description = "Recurso responsavel pelo gerenciamento dos filmes")
public class MovieController {

    private final MovieService movieService;

    @Operation(summary="Cria novo Filme")
    @ApiResponse(responseCode = "201", description = "Salva um novo Filme",
    content = @Content(schema = @Schema(implementation = MovieResponse.class))
    )
    @PostMapping
    public ResponseEntity<MovieResponse>save(@Valid @RequestBody MovieRequest movie){
       Movie saved = movieService.save(MovieMapper.toMovie(movie));
       return ResponseEntity.ok(MovieMapper.toMovieResponse(saved));
    }

    @ApiResponse(responseCode ="200", description = "Retorna todos filmes cadastrados")
    @Operation(security =@SecurityRequirement(name = "bearerAuth"), summary="Retorna todos filmes cadastrados")
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
