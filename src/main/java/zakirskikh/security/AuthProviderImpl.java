package zakirskikh.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import zakirskikh.dao.SystemUserDao;
import zakirskikh.model.SystemUser;

import java.util.ArrayList;
import java.util.List;

public class AuthProviderImpl implements AuthenticationProvider {

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * Авторизуем пользователя
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();

        SystemUser systemUser = SystemUserDao.get(email);
        if (systemUser == null) {
            throw new UsernameNotFoundException("SystemUser not found");
        }
        String password = authentication.getCredentials().toString();
        if (!encoder.matches(password, systemUser.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(systemUser.getRole().toString()));

        return new UsernamePasswordAuthenticationToken(systemUser, null, authorities);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }

    public static SystemUser getCurrentUser(){
        return (SystemUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
