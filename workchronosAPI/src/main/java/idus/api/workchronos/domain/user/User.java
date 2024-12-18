package idus.api.workchronos.domain.user;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

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

    public User(UUID id, String name, String email, String password, UserRole role, String phone, LocalDate birthDate, LocalDateTime startDate, LocalDateTime endDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id == null ? UUID.randomUUID() : id;
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

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
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
            final LocalDateTime endDate,
            final LocalDateTime createdAt,
            final LocalDateTime updatedAt
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
                createdAt,
                updatedAt
        );
    }
}
