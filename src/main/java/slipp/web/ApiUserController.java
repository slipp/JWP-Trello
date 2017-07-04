package slipp.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import slipp.domain.User;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class ApiUserController {
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        return user;
    }
}
