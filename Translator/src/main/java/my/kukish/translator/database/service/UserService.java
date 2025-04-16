package my.kukish.translator.database.service;

import lombok.RequiredArgsConstructor;
import my.kukish.translator.database.repository.UserRepository;
import my.kukish.translator.dto.UserCreateEditDto;
import my.kukish.translator.dto.UserReadDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

}
