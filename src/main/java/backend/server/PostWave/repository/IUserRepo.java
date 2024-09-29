package backend.server.PostWave.repository;

import backend.server.PostWave.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IUserRepo extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findUserById(Long id);
    List<User> findByRole(String role);
}
