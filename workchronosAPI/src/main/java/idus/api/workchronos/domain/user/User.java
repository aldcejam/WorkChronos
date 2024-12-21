package idus.api.workchronos.domain.user;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class User {
    private final UUID id;
    private final String name;
    private final String email;
    private final String password;
    private final UserRole role;
    private final String phone;
    private final LocalDate birthDate;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    private User(UUID id, String name, String email, String password, UserRole role, String phone, LocalDate birthDate, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
        this.phone = phone;
        this.birthDate = birthDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User NewUser(String name, String email, String password, UserRole role, String phone, LocalDate birthDate, LocalDateTime startDate, LocalDateTime endDate) {
        return new User(null, name, email, password, role, phone, birthDate, startDate, endDate, null, null);
    }

    public static User with(
            final UUID id,
            final String name,
            final String email,
            final String password,
            final UserRole role,
            final String phone,
            final LocalDate birthDate,
            final LocalDateTime startDate,
            final LocalDateTime endDate
    ) {
        return new User(
                id,
                name,
                email,
                password,
                role,
                phone,
                birthDate,
                startDate,
                endDate,
                null,
                null
        );
    }
}
