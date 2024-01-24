package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.VehicleEntity;

public interface IVehicleRepository extends JpaRepository<VehicleEntity, Long> {

	VehicleEntity findByPlate(String plate);
	
	List<VehicleEntity> findAllByStatus(String status);
	
	@Query("select v from VehicleEntity v where v.status='ENABLE' and v.parkingEntity.id = :idParking")
    List<VehicleEntity> findAllByParkingId(Long idParking);
	
	@Query("select v from VehicleEntity v where v.status='ENABLE' and NOT EXISTS (SELECT ph FROM ParkingHistoryEntity ph WHERE v.parkingEntity.id = ph.parkingEntity.id and v.id=ph.vehicleEntity.id)")
    List<VehicleEntity> vehiclesParkedFirstTime();
	
	@Query("SELECT v FROM VehicleEntity v WHERE v.plate LIKE %:plate_search% and v.status='ENABLE'")
	List<VehicleEntity> searchVehicles(@Param("plate_search") String plateSearch);
	
	@Query(nativeQuery = true,
			value = "SELECT\r\n"
					+ "  CASE\r\n"
					+ "    WHEN COALESCE(SUM(CASE WHEN v.status = 'ENABLE' THEN 1 ELSE 0 END), 0) >= pa.vehicle_limit THEN TRUE\r\n"
					+ "    ELSE FALSE\r\n"
					+ "  END AS esta_lleno\r\n"
					+ "FROM\r\n"
					+ "  public.parking pa\r\n"
					+ "LEFT JOIN\r\n"
					+ "  public.vehicle v ON pa.id = v.parking_id\r\n"
					+ "WHERE\r\n"
					+ "  pa.id = :id_parking\r\n"
					+ "GROUP BY\r\n"
					+ "  pa.id, pa.vehicle_limit")
	Boolean verifyLimitVehicles(@Param("id_parking") Long idParking);

}