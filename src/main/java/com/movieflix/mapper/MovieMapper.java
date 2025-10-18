package com.movieflix.mapper;

import com.movieflix.controller.request.MovieRequest;
import com.movieflix.controller.response.MovieResponse;
import com.movieflix.entity.Category;
import com.movieflix.entity.Movie;
import com.movieflix.entity.Streaming;
import lombok.Builder;
import lombok.experimental.UtilityClass;

import java.util.List;

@Builder
@UtilityClass
public class MovieMapper {

    public static Movie toMovie(MovieRequest request){
        List<Category>list  = request.categories().stream()
                .map(categoryId -> Category.builder().id(categoryId).build())
                .toList();

        List<Streaming> streamings  = request.categories().stream()
                .map(streamingId -> Streaming.builder().id(streamingId).build())
                .toList();

        return Movie.builder()
                .title(request.title())
                .rating(request.rating())
                .description(request.description())
                .releaseDate(request.releaseDate())
                .categories(list)
                .streamings(streamings)
                .build();
    }

    public static MovieResponse toMovieResponse(Movie movie){
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .description(movie.getDescription())
                .rating(movie.getRating())
                .releaseDate(movie.getReleaseDate())
                .categories(movie.getCategories().stream()
                        .map(category -> CategoryMapper.toCategoryResponse(category)
                        ).toList())
                .streamings(movie.getStreamings().stream().map(streaming -> StreamingMapper.toStreamingResponse(streaming))
                .toList())
                .build();
    }
}
