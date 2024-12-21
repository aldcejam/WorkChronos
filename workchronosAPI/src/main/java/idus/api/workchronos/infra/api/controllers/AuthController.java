package idus.api.workchronos.infra.api.controllers;

import idus.api.workchronos.infra.persistence.user.UserDB;
import idus.api.workchronos.infra.persistence.user.UserRepository;
import idus.api.workchronos.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    private AuthenticationManager authenticationManager;
    private UserRepository repository;
    private TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, UserRepository repository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid UserLoginInput data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserDB) auth.getPrincipal());

        return ResponseEntity.ok(new UserLoginOutput(token));
    }

    @PostMapping("/register")
    public void register() {

    }
}

record UserLoginInput(String email, String password) {
}

record UserLoginOutput(String token) {
}
