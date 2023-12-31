package com.ptn.prueba_tecnica_nelumbo.domain.model;

public class SendMailModel {
    
    private String email;

    private String message;
    
    private String plate;
    
    private String parkingName;


	public SendMailModel() {
	}

	public SendMailModel(String email, String message, String plate, String parkingName) {
		this.email = email;
		this.message = message;
		this.plate = plate;
		this.parkingName = parkingName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPlate() {
		return plate;
	}

	public void setPlate(String plate) {
		this.plate = plate;
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

}
