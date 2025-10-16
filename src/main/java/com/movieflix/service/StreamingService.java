package com.movieflix.service;

import com.movieflix.entity.Category;
import com.movieflix.entity.Streaming;
import com.movieflix.repository.StreamingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StreamingService {
    private final StreamingRepository streamingRepository;


    public List<Streaming> findAll() {
        return streamingRepository.findAll();
    }

    public Streaming saveStreaming(Streaming streaming){
        return streamingRepository.save(streaming);
    }

    public Optional<Streaming> findById(long id) {
        return streamingRepository.findById(id);
    }

    public void deleteById(Long id){
        streamingRepository.deleteById(id);
    }
}
