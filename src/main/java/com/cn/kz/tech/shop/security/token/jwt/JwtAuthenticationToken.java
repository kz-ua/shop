package com.cn.kz.tech.shop.security.token.jwt;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by kz on 31.03.17.
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {
    private String token;
    public JwtAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }
    public String getToken() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return null;
    }
    @Override
    public Object getPrincipal() {
        return null;
    }
}
