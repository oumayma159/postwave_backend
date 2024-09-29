package backend.server.PostWave.controller;

import backend.server.PostWave.dto.LikeDto;
import backend.server.PostWave.mapper.LikeMapper;
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
    @Autowired
    private LikeMapper likeMapper;

    @PostMapping("/add_like/{postId}")
    public ResponseEntity<LikeDto> likePost(@PathVariable long postId) {
        Like like = likeService.addLike(postId);
        LikeDto likeDto =likeMapper.toDto(like);
        return new ResponseEntity<>(likeDto,HttpStatus.CREATED);
    }

    @DeleteMapping("/unlike/{postId}")
    public ResponseEntity<?> unlikePost(@PathVariable Long postId) {
        likeService.deleteLike(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}