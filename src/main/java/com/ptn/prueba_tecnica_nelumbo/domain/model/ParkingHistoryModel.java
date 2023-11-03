package com.ptn.prueba_tecnica_nelumbo.domain.model;

import java.util.Date;

public class ParkingHistoryModel {
	
    private Long id;
    
    private VehicleModel vehicleModel;
    
    private ParkingModel parkingModel;
    
    private Date departureDate;

    private Date creation;

	public ParkingHistoryModel(Long id, VehicleModel vehicleModel, ParkingModel parkingModel, Date departureDate,
			Date creation) {
		this.id = id;
		this.vehicleModel = vehicleModel;
		this.parkingModel = parkingModel;
		this.departureDate = departureDate;
		this.creation = creation;
	}

	public ParkingHistoryModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VehicleModel getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(VehicleModel vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public ParkingModel getParkingModel() {
		return parkingModel;
	}

	public void setParkingModel(ParkingModel parkingModel) {
		this.parkingModel = parkingModel;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

}
