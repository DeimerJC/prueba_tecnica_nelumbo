package com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.security;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.Constants;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.jwt.CustomAuthorizationFilter;

@Configuration
public class SpringSecurityConfig {

	@Autowired
	private CustomAuthorizationFilter customAuthorizationFilter;
	
	@Value("${jwtsecret}")
    private String secret;

    @Value("${expiration}")
    private int expiration;
    
    private static final String[] AUTH_OPENAPI_LIST = {
    		"/swagger-ui/**",
    		"/v3/api-docs/**",
    		"/swagger-ui.html",
    		//"/v3/api/docs.yaml"
    };

	@Bean
	SecurityFilterChain web(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeHttpRequests((authorize) -> authorize.
				requestMatchers("/api/v1/auth/login").permitAll()
				.requestMatchers(AUTH_OPENAPI_LIST).permitAll()
	            .requestMatchers(HttpMethod.POST, "/api/v1/user").hasAuthority(Constants.ROLE_ADMIN)
	            .requestMatchers(HttpMethod.POST, "/api/v1/parking").hasAuthority(Constants.ROLE_ADMIN)
	            .requestMatchers(HttpMethod.PUT, "/api/v1/parking").hasAuthority(Constants.ROLE_ADMIN)
	            .requestMatchers("/api/v1/parkings").hasAuthority(Constants.ROLE_ADMIN)
	            .requestMatchers(HttpMethod.GET, "/api/v1/parking/{idParking}").hasAuthority(Constants.ROLE_ADMIN)
	            .requestMatchers(HttpMethod.DELETE, "/api/v1/parking/{idParking}").hasAuthority(Constants.ROLE_ADMIN)
	            .requestMatchers("/api/v1/vehicle/parking-income").hasAuthority(Constants.ROLE_SOCIO)
	            .requestMatchers("/api/v1/vehicle/parking-exit").hasAuthority(Constants.ROLE_SOCIO)
				.anyRequest().authenticated())
				.cors(withDefaults())
				.addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

}
