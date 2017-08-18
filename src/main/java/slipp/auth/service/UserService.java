package slipp.auth.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import slipp.auth.domain.Authentication;
import slipp.auth.domain.UnAuthenticationException;
import slipp.auth.domain.User;
import slipp.auth.domain.UserRepository;

@Service
public class UserService {
    @Resource(name = "userRepository")
    private UserRepository userRepository;

    public User login(Authentication authentication) throws UnAuthenticationException {
        User user = userRepository.findByUserId(authentication.getUserId());
        if (user == null) {
            throw new UnAuthenticationException("존재하지 않는 사용자입니다.");
        }

        if (!user.matchPassword(authentication.getPassword())) {
            throw new UnAuthenticationException("비밀번호가 틀립니다.");
        }
        return user;
    }
}