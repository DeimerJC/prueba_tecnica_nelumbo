package com.ptn.prueba_tecnica_nelumbo.domain.model;

import java.util.Date;

public class RoleModel {
	
    private Long id;
    
    private String roleName;

    private String description;
    
    private String status;
    
    private Date creation;

	public RoleModel(Long id, String roleName, String description, String status, Date creation) {
		this.id = id;
		this.roleName = roleName;
		this.description = description;
		this.status = status;
		this.creation = creation;
	}

	public RoleModel() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String descripction) {
		this.description = descripction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreation() {
		return creation;
	}

	public void setCreation(Date creation) {
		this.creation = creation;
	}

}
