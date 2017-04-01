package com.cn.kz.tech.shop.security.token;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by kz on 01.04.17.
 */
public interface TokenGenerator {

    String generateToken(UserDetails userDetails);

}
