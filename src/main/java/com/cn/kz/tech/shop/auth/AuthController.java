package com.cn.kz.tech.shop.auth;

import com.cn.kz.tech.shop.security.token.TokenResponse;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityDataConfiguration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kz on 31.03.17.
 */
@RestController
public class AuthController {
    private static final Logger logger =  LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @RequestMapping(value="/auth",  method= RequestMethod.POST/*, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE*/)
    public @ResponseBody
    TokenResponse authUser(@RequestParam String username, @RequestParam String password){
        Authentication resultOfAuthentication = tryToAuthenticateWithUsernameAndPassword(Optional.<String>fromNullable(username), Optional.<String>fromNullable(password));
        SecurityContextHolder.getContext().setAuthentication(resultOfAuthentication);
        TokenResponse tokenResponse = new TokenResponse(resultOfAuthentication.getDetails().toString());
        return tokenResponse;
    }

    private Authentication tryToAuthenticateWithUsernameAndPassword(Optional username, Optional password) {
        UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(username, password);
        return tryToAuthenticate(requestAuthentication);
    }

    private Authentication tryToAuthenticate(Authentication requestAuthentication) {

        Authentication responseAuthentication = authenticationManager.authenticate(requestAuthentication);
        if (responseAuthentication == null || !responseAuthentication.isAuthenticated()) {
            throw new InternalAuthenticationServiceException("Unable to authenticate Domain User for provided credentials");
        }
        logger.debug("User successfully authenticated");
        return responseAuthentication;
    }

}