package com.movieflix.service;


import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import com.movieflix.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final StreamingService streamingService;


    public Movie save(Movie movie){
        movie.setCategories(this.getCategories(movie.getCategories()));
        movie.setStreamings(this.getStreamings(movie.getStreamings()));
        return movieRepository.save(movie);
    }

    public List<Movie>getAll(){
        return movieRepository.findAll();
    }

    public Optional<Movie> getById(long id){
        return movieRepository.findById(id);
    }

    private List<Category> getCategories(List<Category> categories){

        List<Category> categoryList = new ArrayList<>();
        for(Category category : categories){
            categoryService.findById(category.getId()).ifPresent(categoryList::add);
        }
        return categoryList;
    }

    private List<Streaming> getStreamings(List<Streaming> streamings){

        List<Streaming> streamingList = new ArrayList<>();
        for(Streaming streaming : streamings){
            streamingService.findById(streaming.getId()).ifPresent(streamingList::add);
        }
        return streamingList;
    }

    public Optional<Movie> update(Long id, Movie movie){
        Optional<Movie> byId = movieRepository.findById(id);

        if(byId.isPresent()){
            Movie updatedMovie = byId.get();

            List<Category> categories = this.getCategories(movie.getCategories());
            List<Streaming> streamings = this.getStreamings(movie.getStreamings());


            updatedMovie.setId(id);
            updatedMovie.setTitle(movie.getTitle());
            updatedMovie.setRating(movie.getRating());
            updatedMovie.setDescription(movie.getDescription());
            updatedMovie.setReleaseDate(movie.getReleaseDate());

            updatedMovie.setCategories(categories);
            updatedMovie.setStreamings(streamings);

            movieRepository.save(updatedMovie);
            return Optional.of(updatedMovie);
        }
        return Optional.empty();
    }

    public List<Movie> findByCategory(Long categoryId){
    return movieRepository.findMovieByCategories(List.of(Category.builder().id(categoryId).build()));
    }

    public void deleteById(Long id){
        movieRepository.deleteById(id);
    }
}
