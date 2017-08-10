package slipp.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.oauth2.resource.FixedPrincipalExtractor;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

@Component
@Slf4j
public class AuthenticationEventListener {
    private final PrincipalExtractor principalExtractor = new FixedPrincipalExtractor();

    @Resource(name = "customUserDetailsService")
    private UserDetailsService userDetailsService;

    @EventListener
    public void handleAuthenticationSuccess(InteractiveAuthenticationSuccessEvent event) {
        if (!(event.getAuthentication() instanceof OAuth2Authentication)) {
            return;
        }

        OAuth2Authentication authentication = (OAuth2Authentication) event.getAuthentication();
        authentication.getPrincipal();

        Map<String, Object> map = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
        log.debug("authentication details : {}", map);

        UserDetails userDetails = getUser(map);

//        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities()));
    }

    private UserDetails getUser(Map<String, Object> map) {
        String username = principalExtractor.extractPrincipal(map).toString();
        log.debug("loaded username : {}", username);

//        UserDetails user;
//
//        try {
//            user = userDetailsService.loadUserByUsername(username);
//        } catch (UsernameNotFoundException e) {
//            User newUser = getNewUser(username);
//
//            Optional.ofNullable(map.get("name")).map(Object::toString).ifPresent(newUser::setName);
//            Optional.ofNullable(map.get("email")).map(Object::toString).ifPresent(newUser::setEmail);
//            Optional.ofNullable(map.get("avatar_url")).map(Object::toString).ifPresent(newUser::setAvatarUrl);
//
//            logger.info("Creating new user: " + username);
//            userRepository.save(newUser);
//
//            user = userDetailsService.loadUserByUsername(username);
//        }
//        return user;

        return null;
    }
}