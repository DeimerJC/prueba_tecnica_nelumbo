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
@Table(name = "Vehicle",
uniqueConstraints=@UniqueConstraint(columnNames={"plate"}))
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleEntity {
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(nullable = false)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "parking_id")
    private ParkingEntity parkingEntity;
    
    private String plate;

    private String status;

    @Column(name = "date_admission")
    private Date dateAdmission;

    private Date creation;
    
}
