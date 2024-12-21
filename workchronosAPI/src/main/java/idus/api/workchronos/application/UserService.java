package idus.api.workchronos.application;

import idus.api.workchronos.domain.user.User;
import idus.api.workchronos.dtos.user.CreateUserInput;
import idus.api.workchronos.infra.persistence.user.UserDB;
import idus.api.workchronos.infra.persistence.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(CreateUserInput user) {
        User newUser = User.NewUser(
                user.name(),
                user.email(),
                user.password(),
                user.role(),
                user.phone(),
                user.birthDate(),
                user.startDate(),
                user.endDate()
        );

        UserDB UserDBInstance = new UserDB();
        UserDB userDB = UserDBInstance.fromUser(newUser);

        userRepository.save(userDB);
    }

    public User getUserById(UUID id) {
        UserDB user = userRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "User with ID " + id + " not found"));
        return user.toDomain();
    }
}
