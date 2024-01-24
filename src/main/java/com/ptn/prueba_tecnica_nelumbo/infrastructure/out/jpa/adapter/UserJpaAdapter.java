package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.adapter;

import java.util.Collections;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.ptn.prueba_tecnica_nelumbo.domain.model.UserModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IUserPersistencePort;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.UserEntity;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository.IUserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {

    private final IUserRepository iUserRepository;
    private final IUserEntityMapper iUserEntityMapper;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public UserModel saveUser(UserModel userModel) {
    	userModel.setPass(passwordEncode(userModel.getPass()));
        return iUserEntityMapper.toModel(iUserRepository.save(iUserEntityMapper.toEntity(userModel)));
    }
    
    public String passwordEncode(String pass) {
    	return passwordEncoder.encode(pass);
    }

    @Override
    public List<UserModel> getAllUsers() {
        List<UserEntity> entityList = iUserRepository.findAll();
        if (entityList.isEmpty()) {
            Collections.emptyList();
        }
        return iUserEntityMapper.toModelList(entityList);
    }

	@Override
	public UserModel getUser(Long userId) {
        return iUserEntityMapper.toModel(iUserRepository.findById(userId)
        		.orElse(null));
	}

	@Override
	public UserModel updateUser(UserModel userModel) {
		return iUserEntityMapper.toModel(iUserRepository.save(iUserEntityMapper.toEntity(userModel)));
    }

	@Override
	public void deleteUser(Long userId) {
		iUserRepository.deleteById(userId);
	}

	@Override
	public UserModel getByUsername(String username) {
		return iUserEntityMapper.toModel(iUserRepository.findByEmail(username));
	}

	@Override
	public UserModel getByIdentification(String identification) {
		return iUserEntityMapper.toModel(iUserRepository.findByIdentification(identification));
	}
    
}