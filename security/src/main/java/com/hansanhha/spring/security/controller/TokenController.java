package com.hansanhha.spring.security.controller;

import com.hansanhha.spring.security.token.jwt.JwtTokenService;
import com.hansanhha.spring.security.token.TokenAccessor;
import com.hansanhha.spring.security.token.TokenRequest;
import com.hansanhha.spring.security.token.TokenType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class TokenController {

    private final JwtTokenService tokenService;

    @PostMapping("/api/get-token")
    public Map<String, String> getToken(@RequestBody TokenRequest tokenRequest) {
        log.info(this.getClass().getSimpleName());
        log.info("- requested get token");
        log.info("- accessTokenAccessId: {}", tokenRequest.getAccessToken());
        log.info("- refreshTokenAccessId: {}", tokenRequest.getRefreshToken());

        TokenAccessor<Jwt, String> accessTokenAccessor = tokenService.loadTokenByAccessId(TokenType.ACCESS_TOKEN, tokenRequest.getAccessToken());
        TokenAccessor<Jwt, String> refreshTokenAccessor = tokenService.loadTokenByAccessId(TokenType.REFRESH_TOKEN, tokenRequest.getRefreshToken());

        log.info("- return tokens");

        return Map.of(
                accessTokenAccessor.getTokenType().getType(), accessTokenAccessor.get().getTokenValue(),
                refreshTokenAccessor.getTokenType().getType(), refreshTokenAccessor.get().getTokenValue(),
                "issuedAt", accessTokenAccessor.get().getIssuedAt().toString(),
                "expiresAt", accessTokenAccessor.get().getExpiresAt().toString()
        );
    }
}
