package cn.dox.jwt.util;

import cn.dox.jwt.model.entity.User;
import com.google.common.collect.Maps;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

/**
 * @author: weidx
 * @date: 2019/6/3
 */
@Component
public class JwtTokenUtil implements Serializable {
    public static final String CLAIM_KEY_USERNAME = "sub";
    /**
     * 五天
     */
    private static final long EXPIRATION_TIME = 5 * 24 * 60 * 60 * 1000;

    private static final String SECRET = "secret";

    /**
     * 签发jwt
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = Maps.newHashMap();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(Instant.now().toEpochMilli() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();


    }

    /**
     * 验证jwt
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        User user = (User) userDetails;
        String username = getUsernameFromToken(token);
        return username.equals(user.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 校验token是否过去
     * @param token
     * @return
     */
    public boolean isTokenExpired(String token) {
        Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /**
     * 从token中获取时间
     * @param token
     */
    public Date getExpirationDateFromToken(String token) {
        Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration;
    }

    /**
     * 根据token获取username
     * @param token
     * @return
     */
    public String getUsernameFromToken(String token) {
        String username = getClaimsFromToken(token).getSubject();
        return username;
    }

    /**
     * 解析jwt
     * @param token
     * @return
     */
    public Claims getClaimsFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
