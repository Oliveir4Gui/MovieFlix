package com.movieflix.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.movieflix.controller.response.CategoryResponse;
import com.movieflix.controller.response.StreamingResponse;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record MovieRequest(
        String title,
        String description,
        Double rating,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate releaseDate,
        List<Long> categories,
        List<Long> streamings
) {
}
