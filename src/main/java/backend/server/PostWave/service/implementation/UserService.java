package backend.server.PostWave.service.implementation;


import backend.server.PostWave.exception.UserNotFoundException;
import backend.server.PostWave.model.User;
import backend.server.PostWave.repository.IUserRepo;
import backend.server.PostWave.service.IUserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    IUserRepo userRepo;

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User findUserById(Long id) {
        return userRepo.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
    }

    public User findUserByEmail(String email) {
        return userRepo.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("User by email " + email + " was not found"));
    }

}
