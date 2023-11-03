package com.ptn.prueba_tecnica_nelumbo.domain.model;

import java.util.Date;

public class ParkingModel {
	
    private Long id;
    
    private UserModel userModel;
    
    private String name;
    
    private Integer vehicleLimit;

    private Double valueHour;

    private Date creation;

	public ParkingModel(Long id, UserModel userModel, String name, Integer vehicleLimit, Double valueHour,
			Date creation) {
		this.id = id;
		this.userModel = userModel;
		this.name = name;
		this.vehicleLimit = vehicleLimit;
		this.valueHour = valueHour;
		this.creation = creation;
	}

	public ParkingModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVehicleLimit() {
		return vehicleLimit;
	}

	public void setVehicleLimit(Integer vehicleLimit) {
		this.vehicleLimit = vehicleLimit;
	}

	public Double getValueHour() {
		return valueHour;
	}

	public void setValueHour(Double valueHour) {
		this.valueHour = valueHour;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

}
