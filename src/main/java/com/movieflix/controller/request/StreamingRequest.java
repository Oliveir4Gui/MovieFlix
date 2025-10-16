package com.movieflix.controller.request;

import lombok.Builder;

@Builder
public record StreamingRequest(Long id, String name) {
}
