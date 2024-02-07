package demo.myblog16.controller;

import demo.myblog16.payload.CommentDto;
import demo.myblog16.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentContriller {

    private CommentService commentService;

    public CommentContriller(CommentService commentService) {
        this.commentService = commentService;


        }
    @PostMapping
    public ResponseEntity<CommentDto>createComment(@RequestBody CommentDto commentDto, @RequestParam long postId){
        CommentDto dto = commentService.createComment(commentDto, postId);
   return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteCommentById(@PathVariable long id){
        commentService.deleteById(id);
        return  new ResponseEntity<>("comment is delete !!",HttpStatus.OK);
    }
    @PutMapping("/{id}/post/{postId}")
    public ResponseEntity<CommentDto>updateComment(@PathVariable long id,@RequestBody CommentDto commentDto,@PathVariable long postId){
        CommentDto dto = commentService.updateComment(id, commentDto,postId);
   return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
