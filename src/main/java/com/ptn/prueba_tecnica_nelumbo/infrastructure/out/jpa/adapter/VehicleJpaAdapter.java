package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.adapter;

import java.util.Collections;
import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.model.VehicleModel;
import com.ptn.prueba_tecnica_nelumbo.domain.spi.IVehiclePersistencePort;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.VehicleEntity;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.mapper.IVehicleEntityMapper;
import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository.IVehicleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class VehicleJpaAdapter implements IVehiclePersistencePort {

    private final IVehicleRepository iVehicleRepository;
    private final IVehicleEntityMapper iVehicleEntityMapper;
    
	@Override
	public VehicleModel saveVehicle(VehicleModel vehicleModel) {
		return iVehicleEntityMapper.toModel(iVehicleRepository.save(iVehicleEntityMapper.toEntity(vehicleModel)));
    }
	
	@Override
	public List<VehicleModel> getAllVehicles() {
		List<VehicleEntity> entityList = iVehicleRepository.findAll();
        if (entityList.isEmpty()) {
            Collections.emptyList();
        }
        return iVehicleEntityMapper.toModelList(entityList);
    }
	
	@Override
	public VehicleModel getVehicle(Long vehicleId) {
		return iVehicleEntityMapper.toModel(iVehicleRepository.findById(vehicleId)
        		.orElse(null));
	}
	
	@Override
	public VehicleModel updateVehicle(VehicleModel vehicleModel) {
		return iVehicleEntityMapper.toModel(iVehicleRepository.save(iVehicleEntityMapper.toEntity(vehicleModel)));
    }
	
	@Override
	public void deleteVehicle(Long vehicleId) {
		iVehicleRepository.deleteById(vehicleId);
	}

}