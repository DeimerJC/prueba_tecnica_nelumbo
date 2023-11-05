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
@Table(name = "Parking_history")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ParkingHistoryEntity {
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(nullable = false)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicleEntity;
    
    @ManyToOne
    @JoinColumn(name = "parking_id")
    private ParkingEntity parkingEntity;

    @Column(name = "date_admission")
    private Date dateAdmission;
    
    @Column(name = "departure_date")
    private Date departureDate;

    private Date creation;
    
}
