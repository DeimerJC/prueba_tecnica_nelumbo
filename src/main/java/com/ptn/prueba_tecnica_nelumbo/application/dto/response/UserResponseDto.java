package com.ptn.prueba_tecnica_nelumbo.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
	
    private Long id;
    
    private RoleResponseDto roleResponseDto;
    
    private String name;
    
    private String lastName;
    
    private String username;
    
    private String identification;

    private String email;
    
    private String pass;
    
}
