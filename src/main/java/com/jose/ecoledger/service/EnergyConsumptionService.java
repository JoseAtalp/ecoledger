package com.jose.ecoledger.service;

import com.jose.ecoledger.dto.CreateEnergyConsumptionRequest;
import com.jose.ecoledger.entity.EnergyConsumptionEntity;
import com.jose.ecoledger.repository.EnergyConsumptionRepository;
import org.springframework.stereotype.Service;

@Service
public class EnergyConsumptionService {
    private final EnergyConsumptionRepository energyConsumptionRepository;

    public EnergyConsumptionService(EnergyConsumptionRepository energyConsumptionRepository) {
        this.energyConsumptionRepository = energyConsumptionRepository;
    }

    public EnergyConsumptionEntity create(CreateEnergyConsumptionRequest request) {
        EnergyConsumptionEntity entity = new EnergyConsumptionEntity(
                request.consumptionValue(),
                request.measurementUnit(),
                request.readingDate());

        return energyConsumptionRepository.save(entity);
    }
}
