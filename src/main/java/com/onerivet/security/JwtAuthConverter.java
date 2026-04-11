package com.onerivet.security;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
 
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        List<String> roles = Optional.ofNullable(jwt.getClaimAsStringList("roles"))
                .orElse(Collections.emptyList());

        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .toList();

        return new JwtAuthenticationToken(jwt, authorities);
    }
} 