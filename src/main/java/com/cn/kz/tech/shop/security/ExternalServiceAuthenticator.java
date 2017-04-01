package com.cn.kz.tech.shop.security;

import com.cn.kz.tech.shop.security.token.AuthenticationWithToken;

public interface ExternalServiceAuthenticator {

    AuthenticationWithToken authenticate(String username, String password);
}
