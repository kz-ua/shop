package com.cn.kz.tech.shop.security.token.jwt;

import com.cn.kz.tech.shop.security.AuthenticatedUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

/**
 * Created by kz on 31.03.17.
 */
@Component
public class JwtUtil {

    public static final String CLAIM_KEY_USERNAME = "sub";
    public static final String CLAIM_KEY_CREATED = "created";
    public static final String CLAIM_KEY_ACCOUNT_ID = "account_id";
    public static final String CLAIM_KEY_ROLE = "scopes";

    @Value("${jwt.secret}")
    private String secret;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     *
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     */
    public AuthenticatedUser parseToken(String token) {
        try {
            Claims body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();

            AuthenticatedUser user = new AuthenticatedUser(Long.parseLong((String) body.get(CLAIM_KEY_ACCOUNT_ID)),
                    (String) body.get(CLAIM_KEY_USERNAME),
                    null,
                    (Collection<? extends GrantedAuthority>) body.get(CLAIM_KEY_ROLE));

            return user;

        } catch (JwtException | ClassCastException e) {
            return null;
        }
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param user the user for which the token will be generated
     * @return the JWT token
     */
    public String generateToken(AuthenticatedUser user) {
        Claims claims = Jwts.claims().setId(UUID.randomUUID().toString());
        claims.put(CLAIM_KEY_ACCOUNT_ID, user.getId());
        claims.put(CLAIM_KEY_USERNAME, user.getUsername());
        claims.put(CLAIM_KEY_ROLE, user.getAuthorities());
        claims.put(CLAIM_KEY_CREATED, new Date().getTime());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }
}

