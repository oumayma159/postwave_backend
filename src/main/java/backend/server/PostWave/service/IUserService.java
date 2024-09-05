package backend.server.PostWave.service;

import backend.server.PostWave.model.User;

import java.util.List;

public interface IUserService {

    List<User> getAllUsers();

    User findUserById(Long id);
}
