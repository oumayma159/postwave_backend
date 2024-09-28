package backend.server.PostWave.controller;

import backend.server.PostWave.dto.LikeDto;
import backend.server.PostWave.model.Like;
import backend.server.PostWave.service.ILikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/like")
public class LikeController {
    @Autowired
    private ILikeService likeService;

    @PostMapping("/add_like/{userId}/{postId}")
    public ResponseEntity<?> likePost(@PathVariable long postId, @PathVariable long userId) {
        likeService.addLike(postId, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/unlike/{postId}")
    public ResponseEntity<?> unlikePost(@PathVariable Long postId) {
        likeService.deleteLike(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/count/{postId}")
    public long getNumberOfLikesForPost(@PathVariable Long postId) {
        return likeService.getNumberOfLikesForPost(postId);
    }
}