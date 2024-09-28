package backend.server.PostWave.service;

import backend.server.PostWave.dto.CommentDto;
import backend.server.PostWave.model.Comment;
import backend.server.PostWave.model.Like;

import java.util.List;

public interface ICommentService {

    Comment addComment(Long postId, CommentDto commentDto);
    List<Comment> getCommentsByPost(Long postId);
    void deleteComment(Long commentId);
}
