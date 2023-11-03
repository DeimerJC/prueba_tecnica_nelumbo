package com.ptn.prueba_tecnica_nelumbo.domain.model;

import java.util.Date;

public class UserModel {
	
    private Long id;
    
    private RoleModel roleModel;
    
    private String name;
    
    private String lastName;
    
    private String identification;

    private String email;
    
    private String pass;
    
    private String status;

    private Date creation;

	public UserModel(Long id, RoleModel roleModel, String name, String lastName,
			String identification, Date creation, String email, String pass) {
		this.id = id;
		this.roleModel = roleModel;
		this.name = name;
		this.lastName = lastName;
		this.identification = identification;
		this.creation = creation;
		this.email = email;
		this.pass = pass;
	}

	public UserModel() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIdentification() {
		return identification;
	}

	public void setIdentification(String identification) {
		this.identification = identification;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

}
