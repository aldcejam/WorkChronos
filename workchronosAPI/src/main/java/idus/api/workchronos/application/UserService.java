package idus.api.workchronos.application;

import idus.api.workchronos.domain.user.User;
import idus.api.workchronos.dtos.user.CreateUserInput;
import idus.api.workchronos.infra.persistence.user.UserDB;
import idus.api.workchronos.infra.persistence.user.UserRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(CreateUserInput user) {
        User newUser = User.NewUser(
                user.name(),
                user.email(),
                user.dailyWorkHours(),
                user.password(),
                user.role(),
                user.phone(),
                user.birthDate(),
                user.startDate(),
                user.endDate()
        );

        UserDB userDB = UserDB.fromUser(newUser);
        userDB.setPassword(passwordEncoder.encode(userDB.getPassword()));

        userRepository.save(userDB);
    }

    public User getUserById(UUID id) {
        UserDB user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found"));
        return user.toDomain();
    }

    public List<User> getAllUsers() {
        List<UserDB> users = userRepository.findAll();
        return users.stream().map(UserDB::toDomain).toList();
    }
}