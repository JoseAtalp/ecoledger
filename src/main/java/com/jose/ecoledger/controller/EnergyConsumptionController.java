package com.jose.ecoledger.controller;

import com.jose.ecoledger.dto.CreateEnergyConsumptionRequest;
import com.jose.ecoledger.dto.EnergyConsumptionResponse;
import com.jose.ecoledger.service.EnergyConsumptionService;
import org.springframework.web.bind.annotation.*;

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
}
