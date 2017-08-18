package slipp.auth.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import slipp.auth.domain.Authentication;
import slipp.auth.domain.User;
import slipp.auth.service.UserService;

@RestController
@RequestMapping("/api")
public class ApiLoginController {
    @Resource(name = "userService")
    private UserService userService;

    @PostMapping("/login")
    public void login(@RequestBody Authentication authentication, HttpSession session) throws Exception {
        User user = userService.login(authentication);
        session.setAttribute("LOGINED_USER", user);
    }
}