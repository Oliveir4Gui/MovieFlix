package com.movieflix.controller.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record StreamingRequest(Long id,@NotEmpty(message = "Name of streaming service is required") String name) {
}
