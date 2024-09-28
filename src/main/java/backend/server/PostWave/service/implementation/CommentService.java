package backend.server.PostWave.service.implementation;


import backend.server.PostWave.dto.CommentDto;
import backend.server.PostWave.exception.UnauthorizedAccessException;
import backend.server.PostWave.model.Comment;
import backend.server.PostWave.model.Post;
import backend.server.PostWave.model.User;
import backend.server.PostWave.repository.ICommentRepo;
import backend.server.PostWave.service.ICommentService;
import backend.server.PostWave.service.IPostService;
import backend.server.PostWave.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService implements ICommentService {

    @Autowired
    private IPostService postService;
    @Autowired
    private IUserService userService;
    @Autowired
    private ICommentRepo commentRepo;

    public Comment addComment(Long postId, CommentDto commentDto){
        Post post = postService.getPostById(postId);
        User user = userService.getCurrentUser();
        String content = commentDto.getContent();
        Comment comment = new Comment();
        comment.setPost(post);
        comment.setUser(user);
        comment.setContent(content);
        return commentRepo.save(comment) ;
    }

    public List<Comment> getCommentsByPost(Long postId){
        Post post = postService.getPostById(postId);
        List<Comment> comments = post.getComments();
        return comments;
    }

    public void deleteComment(Long commentId){
        User authUser = userService.getCurrentUser();
        Optional<Comment> targetComment = commentRepo.findById(commentId) ;
        if (targetComment.isPresent()) {
            if (targetComment.get().getUser().equals(authUser)) {
                commentRepo.deleteById(commentId);
            } else {
                throw new UnauthorizedAccessException("User not authorized to delete this post.");
            }
        }

    }
}
