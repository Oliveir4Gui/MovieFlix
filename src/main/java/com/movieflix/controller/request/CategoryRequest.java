package com.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CategoryRequest(@NotEmpty(message = "category name is required") String name) {
}
