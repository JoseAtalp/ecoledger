package com.jose.ecoledger.repository;

import com.jose.ecoledger.entity.EnergyConsumptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnergyConsumptionRepository extends JpaRepository<EnergyConsumptionEntity, Long> {
}