package demo.myblog16.service;

import demo.myblog16.payload.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto);

    PostDto getPostById(long id);

   List<PostDto> getAllPost(int pageNo, int pageSize, String sortBy, String sortDir);
}
