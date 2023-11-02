package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Role")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RoleEntity {
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(nullable = false)
    private Long id;
    
    @Column(name = "role_name")
    private String roleName;

    private String descripction;
    
    private String status;

}
