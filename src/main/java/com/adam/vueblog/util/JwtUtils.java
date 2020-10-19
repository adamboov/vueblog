package com.adam.vueblog.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author VAIO-adam
 */
@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "adam.jwt")
public class JwtUtils {

    private String secret;

    private long expire;

    private String header;

    /**
     * 生成jwt Token
     */
    public String generateToken(long userId) {
        Date now = new Date();

        //  过期时间
        Date expireTime = new Date(now.getTime() + expire * 1000);

        String result = Jwts.builder().setHeaderParam("typ", "JWT")
                .setSubject(userId + "")
                .setIssuedAt(now)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();

        return result;
    }

    public Claims getClaimByToken(String token) {
        try {

            Claims result = Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token).getBody();
            return result;
        } catch (Exception e) {
            log.debug("Validate is token error", e);
            return null;
        }
    }

    /**
     * token 是否过期
     */
    public boolean isTokenExpired(Date expiration) {
        return expiration.before(new Date());
    }
}
