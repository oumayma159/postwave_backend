package backend.server.PostWave.controller;


import backend.server.PostWave.dto.PostDto;
import backend.server.PostWave.dto.UpdatePostDto;
import backend.server.PostWave.mapper.PostMapper;
import backend.server.PostWave.model.Post;
import backend.server.PostWave.model.User;
import backend.server.PostWave.service.IPostService;
import backend.server.PostWave.service.IUserService;
import backend.server.PostWave.service.implementation.LikeService;
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
    IUserService userService ;

    @Autowired
    PostMapper postMapper ;
    @Autowired
    private LikeService likeService;

    @GetMapping("/all_posts")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        User currentUser = userService.getCurrentUser();
        List<PostDto> postsDto = postMapper.ToDtoList(posts);
        for (PostDto postDto : postsDto) {
            int likesCount = postDto.getLikes() != null ? postDto.getLikes().size() : 0;
            postDto.setNumberLikes(likesCount);

            boolean isLiked = likeService.isPostLikedByUser(postDto.getId(), currentUser.getId());
            postDto.setLiked(isLiked);

        }
        return new ResponseEntity<>(postsDto, HttpStatus.OK);
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

    @GetMapping("/currentUserPosts")
    public ResponseEntity<List<PostDto>> getUserPost() {
        User currentUser = userService.getCurrentUser();
        List<Post> posts = postService.getPostsByUserId(currentUser.getId());
        return new ResponseEntity<>(postMapper.ToDtoList(posts),HttpStatus.OK);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<List<PostDto>> updatePost(
            @PathVariable Long postId,
            @Valid @RequestBody UpdatePostDto requestDto)
    {
        postService.updatePost(postId, requestDto);
        User currentUser = userService.getCurrentUser();
        List<Post> posts = postService.getPostsByUserId(currentUser.getId());
        return new ResponseEntity<>(postMapper.ToDtoList(posts),HttpStatus.OK);

    }

    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<List<PostDto>> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        User currentUser = userService.getCurrentUser();
        List<Post> posts = postService.getPostsByUserId(currentUser.getId());
        return new ResponseEntity<>(postMapper.ToDtoList(posts),HttpStatus.OK);
    }

}
