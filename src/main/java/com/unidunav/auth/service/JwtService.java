package com.unidunav.auth.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import com.unidunav.user.model.User;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {

    private final String SECRET_KEY = "6770b199bd646d4c9851ef4c51a82db72ef8ed3528b0f3abd9f573d295e45c64710be6c1e29a3a33bf93bfa1adaa46ad5818de35336e1fb4b1ad111809ff8219af167afc49dfccdc8b95e9d4e6168ca5c1ccf04043478c984440ed59fcbf5903275c57203ec9a12d13b337b005ed06f654c6d7c3b04a8a119ec92e1958770995a8d17b02425a40daafa50dfbbf9b14fd2a8b26713c2a52a9ef4efcd8ad849b91ab137bef42c38323840b68ab7fec163a2f67a042410b0333d8386361a50028ac8beb4a48a058e4634038aa525c0252653ad1a7294705ad2c5c45d13d95323b7ec3656f08a5407022e12509a7d66de2bf93a103cffcace4b7274d6963a9ce1e60";
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 10; // 10h

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

//    public String generateToken(String email) {
//        return Jwts.builder()
//                .setSubject(email)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
//                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
    
    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", user.getId());
        claims.put("email", user.getEmail());
//        claims.put("roles", user.getRoles());
        claims.put("roles", user.getRoles().stream()
        	    .map(role -> role.getNaziv())
        	    .collect(Collectors.toList()));


        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

//    public boolean isTokenValid(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token);
//            return true;
//        } catch (JwtException | IllegalArgumentException e) {
//            return false;
//        }
//    }
    public boolean isTokenValid(String token, User user) {
        try {
            String extractedEmail = extractEmail(token);
            return extractedEmail.equals(user.getEmail()) &&
                   !isTokenExpired(token);
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();
        return expiration.before(new Date());
    }

}
