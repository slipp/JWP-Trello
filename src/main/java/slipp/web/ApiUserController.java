package slipp.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import slipp.domain.User;
import slipp.domain.UserRepository;

import javax.annotation.Resource;

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
