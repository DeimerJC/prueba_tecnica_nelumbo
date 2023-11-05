package com.ptn.prueba_tecnica_nelumbo.domain.spi;

import java.util.List;

import com.ptn.prueba_tecnica_nelumbo.domain.model.VehicleModel;

public interface IVehiclePersistencePort {
	
	VehicleModel saveVehicle(VehicleModel vehicleModel);

    List<VehicleModel> getAllVehicles();

    VehicleModel getVehicle(Long vehicleId);

    VehicleModel updateVehicle(VehicleModel vehicleModel);

    void deleteVehicle(Long vehicleId);

    VehicleModel getByPlate(String plate); 

	VehicleModel registerIncome(VehicleModel vehicleModel);

	List<VehicleModel> vehiclesByCoincidence(String plateSearch);

	List<VehicleModel> vehiclesParkedFirstTime();
    
}