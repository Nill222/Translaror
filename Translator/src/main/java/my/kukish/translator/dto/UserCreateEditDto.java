package my.kukish.translator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;
import my.kukish.translator.database.entity.Role;
import java.time.ZonedDateTime;

@Value
public class UserCreateEditDto {
    @NotBlank
            @Size(min = 3, max = 30)
    String username;

    @Email
    String email;

    @NotNull
    String passwordHash;
    ZonedDateTime createdAt;
    Role role;
}
