package com.yinnohs.security.jwt.auth.infrastructure.services;

import com.yinnohs.security.jwt.auth.infrastructure.configs.RsaKeyConfigProperties;
import com.yinnohs.security.jwt.auth.infrastructure.models.AccountModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl {

    @Value("${jwt.auth.expiration}")
    private long expirationTimeInMillisAuthToken;
    @Value("${jwt.refresh.expiration}")
    private long expirationTimeInMillisRefreshToken;

    private final RsaKeyConfigProperties keys;

    public String generateAuthToken(AccountModel account){
        Map<String, Object> claims = new HashMap<>();
        claims.put("accountId", account.getId());
        claims.put("accountEmail", account.getEmail());
        var todayZoneTime = LocalDateTime.now().atZone(ZoneId.systemDefault());
        var today = Date.from(todayZoneTime.toInstant());
        var expiration = new Date(today.getTime() + expirationTimeInMillisAuthToken);

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(account.getEmail())// TODO: change as you like.
                .issuedAt(today)
                .expiration(expiration)
                .and()
                .signWith(keys.privateKey())
                .compact();
    }

    public String generateRefreshToken(AccountModel account){
        Map<String, Object> claims = new HashMap<>();
        claims.put("accountId", account.getId());
        claims.put("accountEmail", account.getEmail());
        var todayZoneTime = LocalDateTime.now().atZone(ZoneId.systemDefault());
        var today = Date.from(todayZoneTime.toInstant());
        var expiration = new Date(today.getTime() + expirationTimeInMillisRefreshToken);

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(account.getEmail())// TODO: change as you like.
                .issuedAt(today)
                .expiration(expiration)
                .and()
                .signWith(keys.privateKey())
                .compact();
    }

    public String extractAccountEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractAccountEmail(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(keys.publicKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
}
