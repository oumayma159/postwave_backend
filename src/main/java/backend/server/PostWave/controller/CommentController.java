package backend.server.PostWave.controller;

import backend.server.PostWave.dto.CommentDto;
import backend.server.PostWave.mapper.CommentMapper;
import backend.server.PostWave.model.Comment;
import backend.server.PostWave.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    private ICommentService commentService;
    @Autowired
    private CommentMapper commentMapper ;

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getComments(@PathVariable Long postId) {
        List<Comment> comments = commentService.getCommentsByPost(postId);
        return new ResponseEntity<>(commentMapper.toDtoList(comments), HttpStatus.OK);
    }

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @RequestBody CommentDto commentDto) {
        Comment comment = commentService.addComment(postId, commentDto);
        return new ResponseEntity<>(commentMapper.toDto(comment), HttpStatus.CREATED);
    }

}
