package com.ptn.prueba_tecnica_nelumbo.domain.api;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.model.VehicleModel;

public interface IVehicleServicePort {
	
	VehicleModel saveVehicle(VehicleModel vehicleModel);

    List<VehicleModel> getAllVehicles();

    VehicleModel getVehicle(Long vehicleId);

    VehicleModel updateVehicle(VehicleModel vehicleModel);

    void deleteVehicle(Long vehicleId);

	VehicleModel registerIncome(VehicleModel vehicleModel); 
    
}