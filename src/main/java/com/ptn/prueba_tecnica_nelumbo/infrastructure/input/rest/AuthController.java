package com.ptn.prueba_tecnica_nelumbo.infrastructure.input.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.jwt.JwtUtilService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/login")
@RequiredArgsConstructor
public class AuthController {

//	@Autowired
//	private final AuthenticationManager authenticationManager;

//	@Autowired
//	UserDetailsService usuarioDetailsService;

	@Autowired
	private JwtUtilService jwtUtilService;

	@PostMapping("/authenticate")
	public ResponseEntity<String> authenticate(@RequestParam("username") String user, @RequestParam("password") String pass) {

//		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user, pass));
//
//		final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(user);

//		final String jwt = jwtUtilService.generateToken(userDetails);

//		return ResponseEntity.ok(jwt);
		return null;
	}

}
