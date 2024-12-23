package idus.api.workchronos.infra.persistence.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserDB, UUID> {
    UserDetails findByEmail(String email);
    boolean existsByEmail(String email);
}
