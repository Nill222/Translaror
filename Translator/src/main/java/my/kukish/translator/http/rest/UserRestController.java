package my.kukish.translator.http.rest;

import lombok.RequiredArgsConstructor;
import my.kukish.translator.database.service.UserService;
import my.kukish.translator.dto.UserCreateEditDto;
import my.kukish.translator.dto.UserReadDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @GetMapping
    @ResponseBody
    public List<UserReadDto> findAll() {
        return userService.findAll();
    }

    @GetMapping("/id")
    @ResponseBody
    public UserReadDto findById(@PathVariable("id") Long id) {
        return userService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public UserReadDto crate(@Validated @RequestBody UserCreateEditDto user) {
        userService.create(user);
        return userService.create(user);
    }

    @PutMapping("/id")
    public UserReadDto update(@PathVariable("id") Long id, @Validated @RequestBody UserCreateEditDto user) {
        return userService.update(id, user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

    }

    @DeleteMapping("/id")
    public void delete(@PathVariable("id") Long id) {
        if(!userService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }
}
