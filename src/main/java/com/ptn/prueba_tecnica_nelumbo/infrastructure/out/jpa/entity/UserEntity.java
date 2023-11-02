package com.ptn.prueba_tecnica_nelumbo.infrastructure.out.jpa.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserEntity {
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(nullable = false)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity roleEntity;
    
    private String name;
    
    @Column(name = "last_name")
    private String lastName;
    
    private String username;
    
    private String identification;

    private Date creation;

    private String email;
    
    private String pass;
    
}
