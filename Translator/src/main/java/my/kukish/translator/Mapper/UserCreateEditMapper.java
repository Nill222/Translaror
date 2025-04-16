package my.kukish.translator.Mapper;

import my.kukish.translator.database.entity.User;
import my.kukish.translator.dto.UserCreateEditDto;

public class UserCreateEditMapper implements Mapper<UserCreateEditDto, User > {

    @Override
    public User map(UserCreateEditDto object) {
        User user = new User();
        copy(object, user);
        return user;
    }

    private void copy(UserCreateEditDto object, User user){
        user.setUsername(object.getUsername());
        user.setEmail(object.getEmail());
        user.setPasswordHash(object.getPasswordHash());
        user.setCreatedAt(object.getCreatedAt());
        user.setRole(object.getRole());

    }

    @Override
    public User map(UserCreateEditDto fromObject, User toObject) {
        copy(fromObject, toObject);
        return toObject;
    }
}
