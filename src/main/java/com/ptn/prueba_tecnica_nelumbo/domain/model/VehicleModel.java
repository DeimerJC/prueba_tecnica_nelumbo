package com.ptn.prueba_tecnica_nelumbo.domain.model;

import java.util.Date;

public class VehicleModel {
	
    private Long id;
    
    private ParkingModel parkingModel;
    
    private String plate;

    private String status;

    private Date dateAdmission;

    private Date creation;

	public VehicleModel(Long id, ParkingModel parkingModel, String plate, String status, Date dateAdmission,
			Date creation) {
		this.id = id;
		this.parkingModel = parkingModel;
		this.plate = plate;
		this.status = status;
		this.dateAdmission = dateAdmission;
		this.creation = creation;
	}

	public VehicleModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ParkingModel getParkingModel() {
		return parkingModel;
	}

	public void setParkingModel(ParkingModel parkingModel) {
		this.parkingModel = parkingModel;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDateAdmission() {
		return dateAdmission;
	}

	public void setDateAdmission(Date dateAdmission) {
		this.dateAdmission = dateAdmission;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

}
