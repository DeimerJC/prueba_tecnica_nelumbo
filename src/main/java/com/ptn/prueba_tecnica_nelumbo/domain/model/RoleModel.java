package com.ptn.prueba_tecnica_nelumbo.domain.model;

public class RoleModel {
	
    private Long id;
    
    private String roleName;

    private String descripction;
    
    private String status;

	public RoleModel(Long id, String roleName, String descripction, String status) {
		this.id = id;
		this.roleName = roleName;
		this.descripction = descripction;
		this.status = status;
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

}
