package idus.api.workchronos.infra.persistence.user;

import idus.api.workchronos.domain.user.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Slf4j
public class UserInitializer implements CommandLineRunner {

    @Value("${app.admin.email}")
    private String emailAdmin;
    @Value("${app.admin.password}")
    private String passwordAdmin;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public UserInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (!userRepository.existsByEmail(emailAdmin)) {
            UserDB user = new UserDB();
            user.setName("Admin User");
            user.setEmail(emailAdmin);
            user.setPassword(passwordEncoder.encode(passwordAdmin));
            user.setRole(UserRole.ADMIN);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            userRepository.save(user);
            log.info("Created admin user");
        }
    }
}
