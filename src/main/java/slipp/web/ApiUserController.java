package slipp.web;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import slipp.user.User;
import slipp.user.UserRepository;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class ApiUserController {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        log.debug("User : {}", user);

        return userRepository.save(user);
    }
}
