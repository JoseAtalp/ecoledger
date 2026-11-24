package com.jose.ecoledger.dto;

import com.jose.ecoledger.domain.MeasurementUnit;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record EnergyConsumptionResponse(Long id,
                                        BigDecimal consumptionValue,
                                        MeasurementUnit measurementUnit,
                                        LocalDateTime readingDate) {
}
