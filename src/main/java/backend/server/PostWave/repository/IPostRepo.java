package backend.server.PostWave.repository;

import backend.server.PostWave.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IPostRepo extends JpaRepository<Post, Long> {
    List<Post> findAllByUserId(Long userId);
    Optional<Post> findById(Long id);
    void deleteById(Long id);
}
