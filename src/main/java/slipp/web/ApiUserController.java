package slipp.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import slipp.domain.User;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class ApiUserController {
    @PostMapping("")
    public User create(User user) {
        return user;
    }
}
