package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.security.adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ptn.prueba_tecnica_nelumbo.domain.model.UserModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IUserPersistencePort;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.security.entity.PrincipalUserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceAdapter implements UserDetailsService {

	private final IUserPersistencePort iUserPersistencePort;

	@Override public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel userModel = iUserPersistencePort.getByUsername(username);

		System.err.println("role name**********: "+userModel.getRoleModel().getRoleName());

		List<String> permisos = new ArrayList<String>();
		permisos.add(userModel.getRoleModel().getRoleName());

		List<GrantedAuthority> authorities = null;
		authorities = permisos.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());

		return new PrincipalUserEntity(userModel.getEmail(), userModel.getPass(), true, true, true, true,
				authorities);
	}

}
