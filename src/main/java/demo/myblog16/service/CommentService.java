package demo.myblog16.service;

import demo.myblog16.payload.CommentDto;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto, long postId);

    void deleteById(long id);

    CommentDto updateComment(long id, CommentDto commentDto, long postId);
}
