package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.adapter;

import java.util.Collections;
import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IParkingPersistencePort;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.ParkingEntity;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper.IParkingEntityMapper;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository.IParkingRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParkingJpaAdapter implements IParkingPersistencePort {

    private final IParkingRepository iParkingRepository;
    private final IParkingEntityMapper iParkingEntityMapper;
    
	@Override
	public ParkingModel saveParking(ParkingModel parkingModel) {
		return iParkingEntityMapper.toModel(iParkingRepository.save(iParkingEntityMapper.toEntity(parkingModel)));
    }
	
	@Override
	public List<ParkingModel> getAllParkings() {
		List<ParkingEntity> entityList = iParkingRepository.findAll();
        if (entityList.isEmpty()) {
            Collections.emptyList();
        }
        return iParkingEntityMapper.toModelList(entityList);
    }
	
	@Override
	public ParkingModel getParking(Long parkingId) {
		return iParkingEntityMapper.toModel(iParkingRepository.findById(parkingId)
        		.orElse(null));
	}
	
	@Override
	public ParkingModel updateParking(ParkingModel parkingModel) {
		return iParkingEntityMapper.toModel(iParkingRepository.save(iParkingEntityMapper.toEntity(parkingModel)));
    }
	
	@Override
	public void deleteParking(Long parkingId) {
		iParkingRepository.deleteById(parkingId);
	}

}