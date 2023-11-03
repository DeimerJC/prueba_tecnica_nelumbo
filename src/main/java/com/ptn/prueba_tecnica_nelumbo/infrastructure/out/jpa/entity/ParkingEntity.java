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
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Parking",
uniqueConstraints=@UniqueConstraint(columnNames={"name"}))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParkingEntity {
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(nullable = false)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;
    
    private String name;
    
    @Column(name = "vehicle_limit")
    private Integer vehicleLimit;

    @Column(name = "value_hour")
    private Double valueHour;

    private Date creation;
    
}
