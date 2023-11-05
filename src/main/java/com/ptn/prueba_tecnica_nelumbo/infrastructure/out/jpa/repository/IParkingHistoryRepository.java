package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity.ParkingHistoryEntity;

public interface IParkingHistoryRepository extends JpaRepository<ParkingHistoryEntity, Long> {
	
	@Query("SELECT vehicleEntity.id, COUNT(*) AS cantidad_registros\r\n"
			+ "FROM ParkingHistoryEntity\r\n"
			+ "GROUP BY vehicleEntity.id\r\n"
			+ "ORDER BY cantidad_registros DESC\r\n"
			+ "LIMIT 10")
    List<Map<?,?>> mostRegisteredVehicles();
	
	@Query("SELECT vehicleEntity.id, COUNT(*) AS cantidad_registros\r\n"
			+ "FROM ParkingHistoryEntity\r\n"
			+ "where parkingEntity.id = :id_parking\r\n"
			+ "GROUP BY vehicleEntity.id\r\n"
			+ "ORDER BY cantidad_registros DESC\r\n"
			+ "LIMIT 10")
	List<Map<?,?>> mostRegisteredVehiclesByParking(@Param("id_parking") Long idParking);
	
	@Query("SELECT \r\n"
			+ "    SUM((EXTRACT(EPOCH FROM ph.departureDate) - EXTRACT(EPOCH FROM ph.dateAdmission)) / 3600 * pa.valueHour) AS ganancias_dia\r\n"
			+ "FROM ParkingHistoryEntity ph\r\n"
			+ "INNER JOIN ParkingEntity pa ON ph.parkingEntity.id = pa.id\r\n"
			+ "WHERE \r\n"
			+ "    ph.departureDate IS NOT NULL\r\n"
			+ "    AND pa.id = :id_parking\r\n"
			+ "    AND DATE(ph.departureDate) = CURRENT_DATE")
	Double parkingProfitsDay(@Param("id_parking") Long idParking);
	
	@Query("SELECT \r\n"
			+ "    SUM((EXTRACT(EPOCH FROM ph.departureDate) - EXTRACT(EPOCH FROM ph.dateAdmission)) / 3600 * pa.valueHour) AS ganancias_dia\r\n"
			+ "FROM ParkingHistoryEntity ph\r\n"
			+ "INNER JOIN ParkingEntity pa ON ph.parkingEntity.id = pa.id\r\n"
			+ "WHERE \r\n"
			+ "    ph.departureDate IS NOT NULL\r\n"
			+ "    AND pa.id = :id_parking\r\n"
			+ "    AND FUNCTION('DATE_TRUNC', 'week', ph.departureDate) = FUNCTION('DATE_TRUNC', 'week', CURRENT_TIMESTAMP)")
	Double parkingProfitsWeek(@Param("id_parking") Long idParking);
	
	@Query("SELECT \r\n"
			+ "    SUM((EXTRACT(EPOCH FROM ph.departureDate) - EXTRACT(EPOCH FROM ph.dateAdmission)) / 3600 * pa.valueHour) AS ganancias_dia\r\n"
			+ "FROM ParkingHistoryEntity ph\r\n"
			+ "INNER JOIN ParkingEntity pa ON ph.parkingEntity.id = pa.id\r\n"
			+ "WHERE \r\n"
			+ "    ph.departureDate IS NOT NULL\r\n"
			+ "    AND pa.id = :id_parking\r\n"
			+ "    AND FUNCTION('DATE_TRUNC', 'month', ph.departureDate) = FUNCTION('DATE_TRUNC', 'month', CURRENT_TIMESTAMP)")
	Double parkingProfitsMonth(@Param("id_parking") Long idParking);
	
	@Query("SELECT \r\n"
			+ "    SUM((EXTRACT(EPOCH FROM ph.departureDate) - EXTRACT(EPOCH FROM ph.dateAdmission)) / 3600 * pa.valueHour) AS ganancias_dia\r\n"
			+ "FROM ParkingHistoryEntity ph\r\n"
			+ "INNER JOIN ParkingEntity pa ON ph.parkingEntity.id = pa.id\r\n"
			+ "WHERE \r\n"
			+ "    ph.departureDate IS NOT NULL\r\n"
			+ "    AND pa.id = :id_parking\r\n"
			+ "    AND FUNCTION('DATE_TRUNC', 'year', ph.departureDate) = FUNCTION('DATE_TRUNC', 'year', CURRENT_TIMESTAMP)")
	Double parkingProfitsYear(@Param("id_parking") Long idParking);

}