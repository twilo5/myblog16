package demo.myblog16.serviceImpl;

import demo.myblog16.entity.Comment;
import demo.myblog16.entity.Post;
import demo.myblog16.exception.ResourceNotFoundException;
import demo.myblog16.payload.CommentDto;
import demo.myblog16.repository.CommentRepository;
import demo.myblog16.repository.PostRepository;
import demo.myblog16.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private PostRepository postRepo;
    private  CommentRepository commentRepo;

    private ModelMapper modelMapper;

    public CommentServiceImpl(PostRepository postRepo, CommentRepository commentRepo, ModelMapper modelMapper) {
        this.postRepo = postRepo;
        this.commentRepo = commentRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CommentDto createComment(CommentDto commentDto, long postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post not found with id:" + postId));

Comment comment = new Comment();
comment.setText(commentDto.getText());
comment.setEmail(commentDto.getEmail());
comment.setPost(post);
        Comment save = commentRepo.save(comment);
        CommentDto dto = new CommentDto();
        dto.setId(save.getId());
        dto.setText(save.getText());
        dto.setEmail(save.getEmail());
        return dto;

    }

    @Override
    public void deleteById(long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public CommentDto updateComment(long id, CommentDto commentDto, long postId) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("post not found for id!!:" + id));
        Comment comment = commentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment not found for id:" + id));
        Comment c= mapToEntity(commentDto);
        c.setId(comment.getId());
        c.setPost(post);
        Comment savedComment = commentRepo.save(c);
        return mapToDto(savedComment);
    }
   CommentDto mapToDto(Comment comment){
       CommentDto dto = modelMapper.map(comment, CommentDto.class);
       return dto;
   }
   Comment mapToEntity(CommentDto commentDto){
       Comment comments = modelMapper.map(commentDto, Comment.class);
       return comments;
   }
}
