package backend.server.PostWave.service.implementation;

import backend.server.PostWave.dto.PostDto;
import backend.server.PostWave.dto.UpdatePostDto;
import backend.server.PostWave.exception.UnauthorizedAccessException;
import backend.server.PostWave.exception.UserNotFoundException;
import backend.server.PostWave.mapper.PostMapper;
import backend.server.PostWave.model.Post;
import backend.server.PostWave.model.User;
import backend.server.PostWave.repository.IPostRepo;
import backend.server.PostWave.service.IPostService;
import backend.server.PostWave.service.auth.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PostService implements IPostService {

    @Autowired
    IPostRepo postRepo ;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserService userService;

    public List<Post> getAllPosts(){
        return postRepo.findAll();
    }

    public Post getPostById(Long id) {
        return postRepo.findById(id).orElseThrow(()->new UserNotFoundException("Post not found with id " + id));
    }

    public PostDto createPost(PostDto postDto) {
        Post post = new Post();
//        User user = userService.findUserById(postDto.getUserId());
        User user = userService.getCurrentUser();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setUser(user);
        return postMapper.ToDto(postRepo.save(post));
    }

    public List<Post> getPostsByUserId(Long userId) {
        List<Post> posts = postRepo.findAllByUserId(userId);
        return posts;
    }

    @Override
    public Post updatePost(Long id, UpdatePostDto updatePostDto) {
        Optional<Post> post = postRepo.findById(id);
        if (post.isPresent()) {
            Post existingPost = post.get();
            if (updatePostDto.getTitle() != null) {
                existingPost.setTitle(updatePostDto.getTitle());
            }
            if (updatePostDto.getDescription() != null) {
                existingPost.setDescription(updatePostDto.getDescription());
            }
            return postRepo.save(existingPost);
        } else {
            throw new UserNotFoundException("Post not found with id " + id);
        }
    }

    public void deletePost(Long postId) {
        User authUser = userService.getCurrentUser();
        Optional<Post> targetPost = postRepo.findById(postId) ;
        if (!targetPost.isEmpty()) {
            if (targetPost.get().getUser().equals(authUser)) {
                postRepo.deleteById(postId);
            } else {
                throw new UnauthorizedAccessException("User not authorized to delete this post.");
            }
        }
    }

}
