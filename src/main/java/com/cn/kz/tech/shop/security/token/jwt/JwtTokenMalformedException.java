package com.cn.kz.tech.shop.security.token.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by kz on 31.03.17.
 */
public class JwtTokenMalformedException extends AuthenticationException {
    public JwtTokenMalformedException(String msg) {
        super(msg);
    }
}
