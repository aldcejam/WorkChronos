package idus.api.workchronos.infra.api.controllers;

import idus.api.workchronos.application.UserService;
import idus.api.workchronos.domain.user.User;
import idus.api.workchronos.dtos.user.CreateUserInput;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController()
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping()
    public void createUser(@Valid @RequestBody CreateUserInput user) {
        userService.createUser(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }
}
