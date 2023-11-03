package com.ptn.prueba_tecnica_nelumbo.application.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDto {
	
    private Long id;
	
    private String roleName;

    private String descripction;
    
    private String status;
    
    private Date creation;

}
