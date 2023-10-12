package com.springboot.blog.security;

import com.springboot.blog.exception.BlogApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;
    @Value("${app.jwt-expiration-millisecond}")
    private long jwtExpirationDate;


//    Generate JWT Token
    public String generateToken(Authentication authentication){
        String username=authentication.getName();
        Date currentDate=new Date();
        Date expiryDate= new Date(currentDate.getTime()+jwtExpirationDate);
        String jwtToken=Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(key())
                .compact();
        return jwtToken;
    }
    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

//    Get user name from jwtToken
    public String getUsername(String token){
       Claims claims= Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        String username=claims.getSubject();
        return username;
    }

//    Validate the JWT token
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build().parse(token);
            return true;
        } catch (MalformedJwtException e){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Invalid JWT token");
        }catch (ExpiredJwtException e){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Expired JWT token");
        }catch (UnsupportedJwtException e){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"Unsupported JWT token");
        }catch (IllegalArgumentException e){
            throw new BlogApiException(HttpStatus.BAD_REQUEST,"JWT claims string is empty");
        }

    }
}
