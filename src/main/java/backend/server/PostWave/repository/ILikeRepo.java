package backend.server.PostWave.repository;

import backend.server.PostWave.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ILikeRepo extends JpaRepository<Like, Long> {
    long countByPostId(Long postId);
    Optional<Like> findByPostId(Long postId);
    boolean existsByPostIdAndUserId(Long postId, Long userId);
    Optional<Like> findByPostIdAndUserId(Long postId,Long userId);
}
