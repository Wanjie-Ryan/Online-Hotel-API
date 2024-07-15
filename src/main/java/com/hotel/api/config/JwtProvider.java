package com.hotel.api.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.*;

// Service class for generating and validating JWT tokens
@Service
public class JwtProvider {

    // Secret key used for signing the JWT token
    private SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes(StandardCharsets.UTF_8));

    // Method to generate a JWT token based on the user's authentication details
    public String generatedToken(Authentication auth) {
        // Get the roles from the authentication object
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

        // Convert the roles to a comma-separated string
        String roles = populateAuthorities(authorities);

        // Create a JWT token with an expiration time of 24 hours
        String jwt = Jwts.builder()
                .setIssuedAt(new Date()) // Set the issue date
                .setExpiration(new Date(new Date().getTime() + 86400000)) // Set the expiration date
                .claim("email", auth.getName()) // Add the email claim
                .claim("authorities", roles) // Add the roles claim
                .signWith(key) // Sign the token with the secret key
                .compact(); // Build the token

        return jwt;
    }

    // Method to extract the email from a JWT token
    public String getEmailFromJwtToken(String jwt) {
        // Remove the 'Bearer ' prefix from the token
        jwt = jwt.substring(7);

        // Parse the JWT token to extract the claims
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key) // Set the signing key
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        // Extract the email from the claims
        String email = String.valueOf(claims.get("email"));

        return email;
    }

    // Helper method to convert a collection of GrantedAuthority objects to a comma-separated string
    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();

        // Iterate through the authorities and add their string representations to the set
        for (GrantedAuthority authority : authorities) {
            auths.add(authority.getAuthority());
        }

        // Join the set elements into a single string separated by commas
        return String.join(",", auths);
    }
}
