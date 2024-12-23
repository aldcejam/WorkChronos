package idus.api.workchronos.infra.api.controllers;

import idus.api.workchronos.application.UserService;
import idus.api.workchronos.dtos.user.CreateUserInput;
import idus.api.workchronos.dtos.user.LoginUserInput;
import idus.api.workchronos.dtos.user.LoginUserOutput;
import idus.api.workchronos.infra.persistence.user.UserDB;
import idus.api.workchronos.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginUserInput data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        UserDB user = (UserDB) auth.getPrincipal();
        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoginUserOutput(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getDailyWorkHours(),
                user.getRole(),
                token
        ));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid CreateUserInput data) {
        userService.createUser(data);
        return ResponseEntity.ok().build();
    }
}

