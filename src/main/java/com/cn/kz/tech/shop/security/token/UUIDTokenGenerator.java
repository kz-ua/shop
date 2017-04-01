package com.cn.kz.tech.shop.security.token;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

/**
 * Created by kz on 01.04.17.
 */
public class UUIDTokenGenerator implements TokenGenerator {
    @Override
    public String generateToken(UserDetails userDetails) {
        return UUID.randomUUID().toString();
    }
}
