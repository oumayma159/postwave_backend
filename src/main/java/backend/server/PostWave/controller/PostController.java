package backend.server.PostWave.controller;


import backend.server.PostWave.model.Post;
import backend.server.PostWave.service.IPostService;
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

    @GetMapping("/all_posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @PostMapping("/add_post")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        Post newPost = postService.createPost(post);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }
}
