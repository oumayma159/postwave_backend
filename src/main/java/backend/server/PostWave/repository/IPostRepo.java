package backend.server.PostWave.repository;

import backend.server.PostWave.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPostRepo extends JpaRepository<Post, Long> {
}
