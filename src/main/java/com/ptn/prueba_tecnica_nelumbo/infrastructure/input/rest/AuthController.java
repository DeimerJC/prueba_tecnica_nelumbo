package com.ptn.prueba_tecnica_nelumbo.infrastructure.input.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.jwt.JwtUtilService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager authenticationManager;

	@Autowired
	UserDetailsService usuarioDetailsService;

	@Autowired
	private JwtUtilService jwtUtilService;

	@Operation(summary = "authentication and obtaining token for user session.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful authentication."),
            @ApiResponse(responseCode = "401", description = "Authentication is required to obtain the requested response.", content = @Content),
            @ApiResponse(responseCode = "403", description = "The client does not have the necessary permissions for certain content.", content = @Content)
    })
	@PostMapping("/login")
	public ResponseEntity<String> authenticate(@RequestParam("username") String user, @RequestParam("password") String pass) {

		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, pass));

		final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(user);

		final String jwt = jwtUtilService.generateToken(userDetails);

		return ResponseEntity.ok(jwt);
	}

}
