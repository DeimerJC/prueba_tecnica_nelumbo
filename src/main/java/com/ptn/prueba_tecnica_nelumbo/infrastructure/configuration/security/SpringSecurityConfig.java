package com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.security;

//import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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

//	@Bean
//	SecurityFilterChain web(HttpSecurity http) throws Exception {
//		http.csrf().disable()
//				.authorizeHttpRequests((authorize) -> authorize
//						.requestMatchers("/api/v1/login/authenticate").permitAll()
//						.requestMatchers("/api/v1/user/findByUsername").permitAll()
//						.requestMatchers(AUTH_OPENAPI_LIST).permitAll()
////	            		.requestMatchers("/**/**").hasRole("ADMIN")
//						.anyRequest().authenticated()
//				)
////				.formLogin().disable()
//		        .cors(withDefaults())
//		        .addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
//		        .sessionManagement((session) -> session
//		            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		        );
//
//		return http.build();
//	}

//	@Bean
//	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//			throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}

}
