package backend.server.PostWave.controller;

import backend.server.PostWave.model.User;
import backend.server.PostWave.service.IUserService;
import backend.server.PostWave.service.auth.JwtService;
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
    @Autowired
    JwtService jwtService ;

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

    @GetMapping("/get_user_by_email/{email}")
    public ResponseEntity<User> findUserByEmail(@PathVariable("email") String email) {
        User user = userService.findUserByEmail(email);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/currentUser")
    public ResponseEntity<User> findCurrentUser(@RequestHeader("Authorization") String authHeader) {
      return ResponseEntity.ok(jwtService.getUserFromToken(authHeader));
    }


}
