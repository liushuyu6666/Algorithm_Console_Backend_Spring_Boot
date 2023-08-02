package com.algorithm.console.Auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class AuthService {
    // TODO: move to SecretManager
    // TODO: update these deprecated methods
    @Value("${jays.jwt.secretKey}")
    private String secretKey;

    public String generateToken(String userId) {
        Date expiration = new Date(System.currentTimeMillis() + 6 * 60 * 60 * 1000); // 6 hours from now

        return Jwts.builder()
                .claim("userId", userId)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, this.secretKey)
                .compact();
    }

    public ObjectId authenticateToken(String token) throws Exception {
        try {

            Claims claims = Jwts.parser()
                    .setSigningKey(this.secretKey)
                    .parseClaimsJws(token)
                    .getBody();

            Date expirationTime = claims.getExpiration();
            Date currentTime = new Date();

            if (expirationTime == null || currentTime.after(expirationTime)) {
                throw new Exception("The token is expired");
            }

            return new ObjectId(claims.get("userId").toString());
        } catch (Exception e) {
            throw new Exception("The token is invalid");
        }
    }
}
