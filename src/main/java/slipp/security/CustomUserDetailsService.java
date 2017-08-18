package slipp.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import slipp.domain.Role;
import slipp.domain.UserRepository;

@Slf4j
@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        log.debug("load username : {}", username);
        slipp.domain.User user = userRepository.findByEmail(username);
        log.debug("loaded User : {}", user);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(slipp.domain.User user, List<GrantedAuthority> authorities) {
        return new User(user.getEmail(), user.getPassword(),
                true, true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(0);
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole()));
        });

        log.debug("authorities : {}", authorities);
        return authorities;
    }
}
