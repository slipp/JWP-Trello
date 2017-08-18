package slipp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    private SecurityUtils() {}
    
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    public static String getUsername() {
        Authentication authentication = getAuthentication();
        
        if (authentication == null) {
            return null;
        }
        
        return authentication.getName();
    }
}
