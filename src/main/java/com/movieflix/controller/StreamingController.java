package com.movieflix.controller;


import com.movieflix.controller.request.StreamingRequest;
import com.movieflix.controller.response.StreamingResponse;
import com.movieflix.entity.Streaming;
import com.movieflix.mapper.CategoryMapper;
import com.movieflix.mapper.StreamingMapper;
import com.movieflix.service.StreamingService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movieflix/streaming")
@RequiredArgsConstructor
public class StreamingController {
    private final StreamingService streamingService;


    @GetMapping()
    public ResponseEntity<List<StreamingResponse>> getAllStreaming(){
        List<Streaming>streamings = streamingService.findAll();
        List<StreamingResponse> list = streamings.stream().map(streaming -> StreamingMapper.toStreamingResponse(streaming)).toList();
        return ResponseEntity.ok(list);
    }

   @GetMapping("/{id}")
    public ResponseEntity<StreamingResponse> getStreamingById(@PathVariable long id){
        return streamingService.findById(id)
                .map( streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
   }

   @PostMapping("/create")
    public ResponseEntity<StreamingResponse> saveStreaming(@RequestBody StreamingRequest request){
        Streaming newStreaming = StreamingMapper.toStreaming(request);
        Streaming savedStreaming = streamingService.saveStreaming(newStreaming);
        return ResponseEntity.ok(StreamingMapper.toStreamingResponse(savedStreaming));
   }

   @DeleteMapping()
    public ResponseEntity<StreamingResponse>deleteStreamingById(@PathVariable long id){
        streamingService.deleteById(id);
        return ResponseEntity.noContent().build();
   }

   @PutMapping("/{id}")
    public ResponseEntity<StreamingResponse> updateStreamingById(@PathVariable Long id, @RequestBody StreamingRequest request){

        return streamingService.updateStreaming(id,StreamingMapper.toStreaming(request))
                .map(streaming -> ResponseEntity.ok(StreamingMapper.toStreamingResponse(streaming)))
                .orElse(ResponseEntity.notFound().build());
   }

}
