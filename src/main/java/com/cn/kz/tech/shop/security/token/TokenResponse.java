package com.cn.kz.tech.shop.security.token;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by kz on 31.03.17.
 */
public class TokenResponse {
    @JsonProperty
    private String token;

    public TokenResponse() {
    }

    public TokenResponse(String token) {
        this.token = token;
    }
}
