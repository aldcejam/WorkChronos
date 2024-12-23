package idus.api.workchronos.dtos.user;

import idus.api.workchronos.domain.user.UserRole;
import jakarta.validation.constraints.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateUserInput(
        @NotBlank(message = "Name cannot be blank")
        @Size(max = 255, message = "Name cannot exceed 255 characters")
        String name,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Invalid email format")
        @Size(max = 255, message = "Email cannot exceed 255 characters")
        String email,

        @NotNull(message = "Daily work hours is required")
        Duration dailyWorkHours,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 8, message = "Password must have at least 8 characters")
        String password,

        @Size(max = 20, message = "Phone number cannot exceed 20 characters")
        String phone,

        @NotNull(message = "Birth date is required")
        @Past(message = "Birth date must be in the past")
        LocalDate birthDate,

        LocalDateTime startDate,

        LocalDateTime endDate,

        @NotNull(message = "Role is required")
        UserRole role
) {
}
