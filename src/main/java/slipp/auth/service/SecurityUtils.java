package slipp.auth.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    private SecurityUtils() {}
    
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    public static Optional<String> getUsername() {
        Authentication authentication = getAuthentication();
        
        if (authentication == null) {
            return Optional.empty();
        }
        
        return Optional.of(authentication.getName());
    }
}
