package com.hotel.api.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class JwtTokenValidator extends OncePerRequestFilter {

    // This method filters incoming HTTP requests to validate JWT tokens.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Retrieve the JWT from the request header.
        String jwt = request.getHeader(JwtConstant.JWT_HEADER);

        // Check if the JWT is present.
        if (jwt != null) {

            // Remove the "Bearer " prefix from the token.
            jwt = jwt.substring(7);

            try {
                // Create a SecretKey object from the secret key bytes.
                SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes(StandardCharsets.UTF_8));

                // Parse the JWT token using the secret key and retrieve the claims.
                Claims claims = Jwts.parserBuilder()
                        .setSigningKey(key)
                        .build()
                        .parseClaimsJws(jwt)
                        .getBody();

                // Extract email and roles from the claims.
                String email = String.valueOf(claims.get("email"));
                String roles = String.valueOf(claims.get("authorities"));
//                logger.info(roles);

                // Convert roles from a comma-separated string to a list of GrantedAuthority objects.
                List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(roles);

                // Create an Authentication object with the email and authorities.
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auth);

                // Set the authentication object in the security context.
                SecurityContextHolder.getContext().setAuthentication(authentication);

            } catch (Exception e) {
                // If any exception occurs while parsing the JWT, throw a BadCredentialsException.
                throw new BadCredentialsException("Invalid token provided");
            }
        }

        // Continue with the next filter in the filter chain.
        filterChain.doFilter(request, response);
    }
}
