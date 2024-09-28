package backend.server.PostWave.service.implementation;

import backend.server.PostWave.exception.UnauthorizedAccessException;
import backend.server.PostWave.model.Like;
import backend.server.PostWave.model.Post;
import backend.server.PostWave.model.User;
import backend.server.PostWave.repository.ILikeRepo;
import backend.server.PostWave.repository.IPostRepo;
import backend.server.PostWave.service.ILikeService;
import backend.server.PostWave.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class LikeService implements ILikeService {

    @Autowired
    private ILikeRepo likeRepository;

    @Autowired
    private IPostRepo postRepository;

    @Autowired
    private IUserService userService;

    public Like addLike(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isEmpty()) {
            throw new IllegalArgumentException("Post not found");
        }
        User user = userService.getCurrentUser() ;
        Like like = new Like();
        like.setPost(postOptional.get());
        like.setUser(user);
        return likeRepository.save(like);
    }

    public void deleteLike(Long postId) {
        User authUser = userService.getCurrentUser();
        Optional<Like> targetLike = likeRepository.findByPostId(postId) ;
        if (!targetLike.isEmpty()) {
            if (targetLike.get().getUser().equals(authUser)) {
                likeRepository.deleteById(targetLike.get().getId());
            } else {
                throw new UnauthorizedAccessException("User not authorized to unlike this post.");
            }
        }
    }


    public long getNumberOfLikesForPost(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}