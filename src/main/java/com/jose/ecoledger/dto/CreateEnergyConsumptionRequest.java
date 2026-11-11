package com.jose.ecoledger.dto;

import com.jose.ecoledger.domain.MeasurementUnit;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public record CreateEnergyConsumptionRequest(BigDecimal consumptionValue,
                                             MeasurementUnit measurementUnit,
                                             LocalDateTime readingDate) {

    public CreateEnergyConsumptionRequest {
        Objects.requireNonNull(consumptionValue, "Consumption value is required");
        Objects.requireNonNull(measurementUnit, "Measurement unit is required");
        Objects.requireNonNull(readingDate, "Reading date is required");

        var now = LocalDateTime.now();
        if (consumptionValue.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Consumption value must be greater than 0");
        }
        if (readingDate.isAfter(now)) {
            throw new IllegalArgumentException("Wrong date / Reading date cannot be in the future");
        }

    }
}
