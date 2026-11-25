package com.jose.ecoledger.service;

import com.jose.ecoledger.dto.CreateEnergyConsumptionRequest;
import com.jose.ecoledger.dto.EnergyConsumptionResponse;
import com.jose.ecoledger.entity.EnergyConsumptionEntity;
import com.jose.ecoledger.repository.EnergyConsumptionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<EnergyConsumptionResponse> findAll() {
        List<EnergyConsumptionEntity> entities = energyConsumptionRepository.findAll();

        List<EnergyConsumptionResponse> responses = new ArrayList<>();
        for (EnergyConsumptionEntity entity : entities) {
            EnergyConsumptionResponse responseDTO = new EnergyConsumptionResponse(
                    entity.getId(),
                    entity.getConsumptionValue(),
                    entity.getMeasurementUnit(),
                    entity.getReadingDate()
            );
            responses.add(responseDTO);
        }
        return responses;
    }

    public EnergyConsumptionResponse findById(Long id) {

        EnergyConsumptionEntity entity = energyConsumptionRepository.findById(id).orElseThrow( () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Energy Consumption NOT FOUND :( " + id));
        return new EnergyConsumptionResponse(
                entity.getId(),
                entity.getConsumptionValue(),
                entity.getMeasurementUnit(),
                entity.getReadingDate()
        );
    }
}
