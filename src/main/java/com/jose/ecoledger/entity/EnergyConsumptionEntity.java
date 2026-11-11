package com.jose.ecoledger.entity;

import com.jose.ecoledger.domain.MeasurementUnit;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name="energy_consumptions")
public class EnergyConsumptionEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal consumptionValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MeasurementUnit measurementUnit;

    @Column(nullable = false)
    private LocalDateTime readingDate;

    protected EnergyConsumptionEntity(){

    }

    public EnergyConsumptionEntity(BigDecimal consumptionValue, MeasurementUnit measurementUnit, LocalDateTime readingDate ){
        this.consumptionValue = consumptionValue;
        this.measurementUnit = measurementUnit;
        this.readingDate = readingDate;
    }

    public Long getId(){
        return this.id;
    }
    public BigDecimal getConsumptionValue(){
        return this.consumptionValue;
    }
    public MeasurementUnit getMeasurementUnit(){
        return this.measurementUnit;
    }
    public LocalDateTime getReadingDate(){
        return this.readingDate;
    }
}
