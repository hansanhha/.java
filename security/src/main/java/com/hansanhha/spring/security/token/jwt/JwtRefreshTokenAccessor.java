package com.hansanhha.spring.security.token.jwt;

import com.hansanhha.spring.security.token.TokenAccessor;
import com.hansanhha.spring.security.token.TokenType;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;

public class JwtRefreshTokenAccessor implements TokenAccessor<Jwt, String> {

    private final Jwt jwt;
    private final String accessId;
    private static final TokenType tokenType = TokenType.REFRESH_TOKEN;

    private JwtRefreshTokenAccessor(Jwt jwt) {
        this.jwt = jwt;
        this.accessId = jwt.getClaim(JwtClaimNames.JTI);
    }

    public static JwtRefreshTokenAccessor from(Jwt jwt) {
        return new JwtRefreshTokenAccessor(jwt);
    }

    @Override
    public Jwt get() {
        return jwt;
    }

    @Override
    public String getAccessId() {
        return accessId;
    }

    @Override
    public TokenType getTokenType() {
        return tokenType;
    }
}
