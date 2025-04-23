package my.kukish.translator.http.rest;

import lombok.RequiredArgsConstructor;
import my.kukish.translator.database.service.UserService;
import my.kukish.translator.dto.UserReadDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    @ResponseBody
    public List<UserReadDto> findAll(){
        return userService.findAll();
    }
}
