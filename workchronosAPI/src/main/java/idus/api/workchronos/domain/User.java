package idus.api.workchronos.domain;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Getter
public class User {
    private final UUID id;
    private final String name;
    private final String email;
    private final String password;
    private final String phone;
    private final Date birthDate;
    private final Date startDate;
    private final Date endDate;
    private final Date createdAt;
    private final Date updatedAt;

    public User(UUID id, String name, String email, String password, String phone, Date birthDate, Date startDate, Date endDate, Date createdAt, Date updatedAt) {
        this.id = id == null ? UUID.randomUUID() : id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birthDate = birthDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static User with(
            final UUID id,
            final String name,
            final String email,
            final String password,
            final String phone,
            final Date birthDate,
            final Date startDate,
            final Date endDate,
            final Date createdAt,
            final Date updatedAt
    ) {
        return new User(
                id,
                name,
                email,
                password,
                phone,
                birthDate,
                startDate,
                endDate,
                createdAt,
                updatedAt
        );
    }
}
