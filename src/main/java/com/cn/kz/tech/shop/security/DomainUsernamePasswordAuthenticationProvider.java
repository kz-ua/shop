package com.cn.kz.tech.shop.security;

import com.cn.kz.tech.shop.security.token.AuthenticationWithToken;
import com.cn.kz.tech.shop.security.token.jwt.JwtUtil;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DomainUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Optional<String> username = (Optional) authentication.getPrincipal();
        Optional<String> password = (Optional) authentication.getCredentials();

        if (!username.isPresent() || !password.isPresent()) {
            throw new BadCredentialsException("Invalid Domain User Credentials");
        }

        AuthenticatedUser user = (AuthenticatedUser) userDetailsService.loadUserByUsername(username.get());

        if (user == null){
            throw new UsernameNotFoundException("Unknown username");
        }

        if (!user.getPassword().equals(password.get())){
            throw new BadCredentialsException("Wrong username or password");
        }

        AuthenticationWithToken resultOfAuthentication = new AuthenticationWithToken(user);
        String newToken = jwtUtil.generateToken(null);
        resultOfAuthentication.setToken(newToken);
        //tokenService.store(newToken, resultOfAuthentication);

        return resultOfAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
