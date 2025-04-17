package my.kukish.translator.dto;

import lombok.Value;
import my.kukish.translator.database.entity.Role;

import java.time.ZonedDateTime;

@Value
public class UserReadDto {
    Long id;
    String username;
    String email;
    String passwordHash;
    ZonedDateTime createdAt;
    Role role;
}
