package backend.server.PostWave.controller;

import backend.server.PostWave.dto.UserDto;
import backend.server.PostWave.mapper.UserMapper;
import backend.server.PostWave.model.User;
import backend.server.PostWave.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    @Autowired
    IUserService userService ;
    @Autowired
    UserMapper userMapper ;

    @GetMapping("/all_users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(userMapper.ToDtoList(users), HttpStatus.OK);
    }
}
