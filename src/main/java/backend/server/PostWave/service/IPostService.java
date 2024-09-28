package backend.server.PostWave.service;

import backend.server.PostWave.dto.PostDto;
import backend.server.PostWave.dto.UpdatePostDto;
import backend.server.PostWave.model.Post;

import java.util.List;

public interface IPostService {

    List<PostDto> getAllPosts();

    Post getPostById(Long id);

    PostDto createPost(PostDto post);

    List<Post> getPostsByUserId(Long userId);

    Post updatePost(Long id, UpdatePostDto updatePostDto);

    void deletePost(Long postId) ;
}
