package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.adapter;

import java.util.Collections;
import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.model.ParkingHistoryModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IParkingHistoryPersistencePort;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.ParkingHistoryEntity;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper.IParkingHistoryEntityMapper;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository.IParkingHistoryRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ParkingHistoryJpaAdapter implements IParkingHistoryPersistencePort {

    private final IParkingHistoryRepository iParkingHistoryRepository;
    private final IParkingHistoryEntityMapper iParkingHistoryEntityMapper;
    
	@Override
	public ParkingHistoryModel saveParkingHistory(ParkingHistoryModel parkingHistoryModel) {
		return iParkingHistoryEntityMapper.toModel(iParkingHistoryRepository.save(iParkingHistoryEntityMapper.toEntity(parkingHistoryModel)));
    }
	
	@Override
	public List<ParkingHistoryModel> getAllParkingHistorys() {
		List<ParkingHistoryEntity> entityList = iParkingHistoryRepository.findAll();
        if (entityList.isEmpty()) {
            Collections.emptyList();
        }
        return iParkingHistoryEntityMapper.toModelList(entityList);
    }
	
	@Override
	public ParkingHistoryModel getParkingHistory(Long parkingHistoryId) {
		return iParkingHistoryEntityMapper.toModel(iParkingHistoryRepository.findById(parkingHistoryId)
        		.orElse(null));
	}
	
	@Override
	public ParkingHistoryModel updateParkingHistory(ParkingHistoryModel parkingHistoryModel) {
		return iParkingHistoryEntityMapper.toModel(iParkingHistoryRepository.save(iParkingHistoryEntityMapper.toEntity(parkingHistoryModel)));
    }
	
	@Override
	public void deleteParkingHistory(Long parkingHistoryId) {
		iParkingHistoryRepository.deleteById(parkingHistoryId);
	}

}