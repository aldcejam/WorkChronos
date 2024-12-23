package idus.api.workchronos.dtos.user;

import idus.api.workchronos.domain.user.UserRole;

import java.time.Duration;
import java.util.UUID;

public record LoginUserOutput(UUID id, String name, String email, Duration dailyWorkHours, UserRole role, String token) {
}
