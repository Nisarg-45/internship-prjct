package com.onerivet.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

//rbac token with auth0 validate custom in my code token verify for expiration type issuer id and audience with custom in my code and access from scratch 
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		 http
         .csrf(csrf -> csrf.disable())
         .authorizeHttpRequests(auth -> auth
                 .requestMatchers("/api/public").permitAll()
                 .requestMatchers("/api/Admin/**").hasRole("Admin")
                 .requestMatchers("/api/plans/**").hasRole("Admin")
                 .anyRequest().permitAll()
         )
         .oauth2ResourceServer(oauth2 ->
                 oauth2.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthConverter()))
         );

     return http.build();
 }
	
	
	@Bean
	public Converter<Jwt, AbstractAuthenticationToken> jwtAuthConverter() {
	    return jwt -> {

	        List<String> roles = jwt.getClaimAsStringList("roles");

	        if (roles == null) roles = List.of();

	        List<SimpleGrantedAuthority> authorities = roles.stream()
	                .map(role -> "ROLE_" + role)
	                .map(SimpleGrantedAuthority::new)
	                .toList();

	        return new JwtAuthenticationToken(jwt, authorities);
	    };
	}
	
	@Bean
	public JwtDecoder jwtDecoder() {

	    String issuer = "https://iamstaging.the-grydd.com/tenants/d9c7d81d-1ec3-4794-88d1-e433f814db4f";

	    NimbusJwtDecoder jwtDecoder = JwtDecoders.fromIssuerLocation(issuer);

	    OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);

	    OAuth2TokenValidator<Jwt> audienceValidator =
	            new AudienceValidator(List.of(
	                "api://8f28d3f7a4324128b077157ba76da99f", 
	                "api://dev-auto-insurance-api", 
	                "account"
	            ));

	    OAuth2TokenValidator<Jwt> validator =
	            new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

	    jwtDecoder.setJwtValidator(validator);

	    return jwtDecoder;
	}
}
