package com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ptn.prueba_tecnica_nelumbo.domain.api.IParkingHistoryServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IParkingServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IRoleServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.api.ISendMailServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IUserServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.api.IVehicleServicePort;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IParkingHistoryPersistencePort;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IParkingPersistencePort;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IRolePersistencePort;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.ISendMailPersistencePort;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IUserPersistencePort;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IVehiclePersistencePort;
import com.ptn.prueba_tecnica_nelumbo.domain.usecase.ParkingHistoryUseCase;
import com.ptn.prueba_tecnica_nelumbo.domain.usecase.ParkingUseCase;
import com.ptn.prueba_tecnica_nelumbo.domain.usecase.RoleUseCase;
import com.ptn.prueba_tecnica_nelumbo.domain.usecase.SendMailUseCase;
import com.ptn.prueba_tecnica_nelumbo.domain.usecase.UserUseCase;
import com.ptn.prueba_tecnica_nelumbo.domain.usecase.VehicleUseCase;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.configuration.jwt.JwtUtilService;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.adapter.ParkingHistoryJpaAdapter;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.adapter.ParkingJpaAdapter;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.adapter.VehicleJpaAdapter;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper.IParkingEntityMapper;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper.IParkingHistoryEntityMapper;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper.IVehicleEntityMapper;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository.IParkingHistoryRepository;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository.IParkingRepository;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository.IRoleRepository;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository.IUserRepository;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository.IVehicleRepository;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.openfeignclient.adapter.SendMailAdapter;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.openfeignclient.client.ISendMailClient;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
	
    private final IUserRepository iUserRepository;
    private final IUserEntityMapper iUserEntityMapper;
    private final IRoleRepository iRoleRepository;
    private final IRoleEntityMapper iRoleEntityMapper;
    private final IVehicleRepository iVehicleRepository;
    private final IVehicleEntityMapper iVehicleEntityMapper;
    private final IParkingRepository iParkingRepository;
    private final IParkingEntityMapper iParkingEntityMapper;
    private final IParkingHistoryRepository iParkingHistoryRepository;
    private final IParkingHistoryEntityMapper iParkingHistoryEntityMapper;
    private final ISendMailClient iSendMailClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtilService jwtUtilService;
    
    @Bean
    public IUserPersistencePort iUserPersistencePort() {
        return new UserJpaAdapter(iUserRepository, iUserEntityMapper, passwordEncoder);
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
    
    @Bean
    public IVehiclePersistencePort iVehiclePersistencePort() {
    	return new VehicleJpaAdapter(iVehicleRepository, iVehicleEntityMapper);
    }
    
    @Bean
    public IVehicleServicePort iVehicleServicePort() {
    	return new VehicleUseCase(iVehiclePersistencePort(), iParkingServicePort(), iParkingHistoryServicePort(), jwtUtilService);
    }
    
    @Bean
    public IParkingPersistencePort iParkingPersistencePort() {
    	return new ParkingJpaAdapter(iParkingRepository, iParkingEntityMapper);
    }
    
    @Bean
    public IParkingServicePort iParkingServicePort() {
    	return new ParkingUseCase(iParkingPersistencePort());
    }
    
    @Bean
    public IParkingHistoryPersistencePort iParkingHistoryPersistencePort() {
    	return new ParkingHistoryJpaAdapter(iParkingHistoryRepository, iParkingHistoryEntityMapper);
    }
    
    @Bean
    public IParkingHistoryServicePort iParkingHistoryServicePort() {
    	return new ParkingHistoryUseCase(iParkingHistoryPersistencePort());
    }
    
    @Bean
    public ISendMailPersistencePort iSendMailPersistencePort() {
    	return new SendMailAdapter(iSendMailClient);
    }
    
    @Bean
    public ISendMailServicePort iSendMailServicePort() {
    	return new SendMailUseCase(iSendMailPersistencePort(), iVehiclePersistencePort());
    }
    
}