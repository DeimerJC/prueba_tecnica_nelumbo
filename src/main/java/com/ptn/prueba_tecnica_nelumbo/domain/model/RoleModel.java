package com.ptn.prueba_tecnica_nelumbo.domain.model;

import java.util.Date;

public class RoleModel {
	
    private Long id;
    
    private String roleName;

    private String descripction;
    
    private String status;
    
    private Date creation;

	public RoleModel(Long id, String roleName, String descripction, String status, Date creation) {
		this.id = id;
		this.roleName = roleName;
		this.descripction = descripction;
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

	public String getDescripction() {
		return descripction;
	}

	public void setDescripction(String descripction) {
		this.descripction = descripction;
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
