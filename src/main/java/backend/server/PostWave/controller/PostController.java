package backend.server.PostWave.controller;


import backend.server.PostWave.dto.PostDto;
import backend.server.PostWave.dto.UpdatePostDto;
import backend.server.PostWave.mapper.PostMapper;
import backend.server.PostWave.model.Post;
import backend.server.PostWave.service.IPostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    @Autowired
    IPostService postService ;

    @Autowired
    PostMapper postMapper ;

    @GetMapping("/all_posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(postMapper.ToDtoList(posts), HttpStatus.OK);
    }

    @GetMapping("/getPost/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable Long postId) {
        Post post = postService.getPostById(postId);
        return new ResponseEntity<>(postMapper.ToDto(post), HttpStatus.OK);
    }

    @PostMapping("/add_post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto post) {
        PostDto newPost = postService.createPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<PostDto>> getUserPost(@PathVariable Long userId) {
        List<Post> posts = postService.getPostsByUserId(userId);
        return new ResponseEntity<>(postMapper.ToDtoList(posts),HttpStatus.OK);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable Long postId,
            @Valid @RequestBody UpdatePostDto requestDto)
    {
        Post updatedPost = postService.updatePost(postId, requestDto);
        return ResponseEntity.ok(postMapper.ToDto(updatedPost));

    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
