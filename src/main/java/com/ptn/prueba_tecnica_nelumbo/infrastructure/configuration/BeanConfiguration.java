package com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ptn.prueba_tecnica_nelumbo.domain.api.IRoleServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IUserServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IRolePersistencePort;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IUserPersistencePort;
import com.ptn.prueba_tecnica_nelumbo.domain.usecase.RoleUseCase;
import com.ptn.prueba_tecnica_nelumbo.domain.usecase.UserUseCase;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository.IRoleRepository;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
	
    private final IUserRepository iUserRepository;
    private final IUserEntityMapper iUserEntityMapper;
    private final IRoleRepository iRoleRepository;
    private final IRoleEntityMapper iRoleEntityMapper;
//    private final PasswordEncoder passwordEncoder;
    
    @Bean
    public IUserPersistencePort iUserPersistencePort() {
        return new UserJpaAdapter(iUserRepository, iUserEntityMapper);
    }

    @Bean
    public IUserServicePort iUserServicePort() {
        return new UserUseCase(iUserPersistencePort());
    }
    
    @Bean
    public IRolePersistencePort iRolePersistencePort() {
    	return new RoleJpaAdapter(iRoleRepository, iRoleEntityMapper);
    }
    
    @Bean
    public IRoleServicePort iRoleServicePort() {
    	return new RoleUseCase(iRolePersistencePort());
    }
    
}