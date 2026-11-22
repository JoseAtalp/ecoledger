package com.jose.ecoledger.service;

import com.jose.ecoledger.dto.CreateEnergyConsumptionRequest;
import com.jose.ecoledger.dto.EnergyConsumptionResponse;
import com.jose.ecoledger.entity.EnergyConsumptionEntity;
import com.jose.ecoledger.repository.EnergyConsumptionRepository;
import org.springframework.stereotype.Service;

@Service
public class EnergyConsumptionService {
    private final EnergyConsumptionRepository energyConsumptionRepository;

    public EnergyConsumptionService(EnergyConsumptionRepository energyConsumptionRepository) {
        this.energyConsumptionRepository = energyConsumptionRepository;
    }

    public EnergyConsumptionResponse create(CreateEnergyConsumptionRequest request) {
        EnergyConsumptionEntity entity = new EnergyConsumptionEntity(
                request.consumptionValue(),
                request.measurementUnit(),
                request.readingDate());
        EnergyConsumptionEntity saved = energyConsumptionRepository.save(entity);

        return new EnergyConsumptionResponse(
                saved.getId(),
                saved.getConsumptionValue(),
                saved.getMeasurementUnit(),
                saved.getReadingDate());
    }
}
