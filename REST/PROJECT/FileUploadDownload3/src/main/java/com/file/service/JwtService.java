package com.file.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {


    private String secretKey = "";

    public JwtService() {
         try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey key = keyGenerator.generateKey();
            secretKey = Base64.getEncoder().encodeToString(key.getEncoded());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);

        String token = Jwts.builder()
                .claims()
                .add(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .and()
                .signWith(getKey())
                .compact();
        return token;
    }

    private SecretKey getKey() {


//        byte[] encodedKey = Base64.getEncoder().encode(secretKey.getBytes());
        byte[] encodedKey = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(encodedKey);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parser().verifyWith(decryptKey(secretKey)).build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey decryptKey(String secretKey) {
        byte[] decode = Decoders.BASE64.decode(secretKey);

        return Keys.hmacShaKeyFor(decode);
    }

    private <T> T extractClaims(String token, Function<Claims,T> claimsResolver){
     Claims claims = extractAllClaims(token);
     return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        String userName = extractUsername(token);
        Boolean isExpired = isTokenExpired(token);
        return (userName.equals(userDetails.getUsername()) && !isExpired);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
}
