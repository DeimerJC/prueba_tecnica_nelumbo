package com.ptn.prueba_tecnica_nelumbo.application.dto.response;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
	
    private Long id;
    
    private RoleResponseDto roleResponseDto;
    
    private String name;
    
    private String lastName;
    
    private String identification;

    private String email;
    
    private String pass;
    
    private String status;

    private Date creation;
    
}
