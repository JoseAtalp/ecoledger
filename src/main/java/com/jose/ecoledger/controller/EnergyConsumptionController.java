package com.jose.ecoledger.controller;

import com.jose.ecoledger.dto.CreateEnergyConsumptionRequest;
import com.jose.ecoledger.dto.EnergyConsumptionResponse;
import com.jose.ecoledger.entity.EnergyConsumptionEntity;
import com.jose.ecoledger.service.EnergyConsumptionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/energy-consumptions")
public class EnergyConsumptionController {

    private final EnergyConsumptionService energyConsumptionService;

    public EnergyConsumptionController(EnergyConsumptionService energyConsumptionService) {
        this.energyConsumptionService = energyConsumptionService;
    }

    @PostMapping
    public EnergyConsumptionResponse create(@RequestBody CreateEnergyConsumptionRequest request) {
        return energyConsumptionService.create(request);
    }

    @GetMapping
    public List<EnergyConsumptionResponse> findAll() {
        return energyConsumptionService.findAll();
    }

    @GetMapping("/{id}")
    public EnergyConsumptionResponse findById(@PathVariable Long id) {

        return energyConsumptionService.findById(id);
    }
}
