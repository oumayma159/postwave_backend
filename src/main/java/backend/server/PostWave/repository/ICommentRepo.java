package backend.server.PostWave.repository;

import backend.server.PostWave.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommentRepo extends JpaRepository<Comment, Long> {
}
