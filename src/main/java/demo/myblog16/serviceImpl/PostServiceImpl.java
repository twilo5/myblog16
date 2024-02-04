package demo.myblog16.serviceImpl;

import demo.myblog16.entity.Post;
import demo.myblog16.payload.PostDto;
import demo.myblog16.repository.PostRepository;
import demo.myblog16.service.PostService;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
        post.setTittle(postDto.getTittle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post savePost = postRepo.save(post);
        PostDto  dto = new PostDto();
        dto.setTittle(savePost.getTittle());
        dto.setDescription(savePost.getDescription());
        dto.setTittle(savePost.getContent());
        return dto;
    }
}
