package backend.server.PostWave.controller;

import backend.server.PostWave.model.User;
import backend.server.PostWave.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    IUserService userService ;

    @GetMapping("/all_users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/get_user/{id}")
    public ResponseEntity<?> findUserById (@PathVariable("id") Long id, HttpServletRequest request) {
        // Extraire l'ID utilisateur du JWT
        Long userIdFromToken = (Long) request.getAttribute("Id");

        if (!id.equals(userIdFromToken)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
        }

        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


}
