package demo.myblog16.controller;

import demo.myblog16.payload.PostDto;
import demo.myblog16.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

@GetMapping
    public ResponseEntity <PostDto>getPostById(@RequestParam("id") long id){
    PostDto dto = postService.getPostById(id);
    return new ResponseEntity<>(dto,HttpStatus.OK);
}
@GetMapping("/getAll")
    public List<PostDto>getAllPost(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int
                                           pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int
                                           pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String
                    sortDir

                                   ){

    List<PostDto> postDtos = postService.getAllPost(pageNo,pageSize,sortBy, sortDir);
    return postDtos;
}
}
