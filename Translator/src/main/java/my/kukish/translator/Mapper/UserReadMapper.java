package my.kukish.translator.Mapper;

import my.kukish.translator.database.entity.User;
import my.kukish.translator.dto.UserReadDto;
import org.springframework.stereotype.Component;

@Component
public class UserReadMapper implements Mapper<User, UserReadDto> {
    @Override
    public UserReadDto map(User object) {
        return new UserReadDto(
                object.getId(),
                object.getUsername(),
                object.getEmail(),
                object.getPasswordHash(),
                object.getCreatedAt(),
                object.getRole()
        );
    }
}
