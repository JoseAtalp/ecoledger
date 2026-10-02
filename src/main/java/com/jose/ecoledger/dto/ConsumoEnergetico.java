package com.jose.ecoledger.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

//Se suelen crear 2 dto distintos, uno para "crear/comprobar"
//y otro para leer (por ejemplo la id una vez devuelta)

public record ConsumoEnergetico(BigDecimal valorConsumo,
                                UnidadMedida unidadMedida,
                                LocalDateTime fechaLectura) {

    //El constructor con sus validaciones(AUTO-VALIDACION del constructor)
    // evita que entre en las capas si hay algún problema.

    public ConsumoEnergetico {
        //Comprobacion para asegurarnos que no llega un null.
        Objects.requireNonNull(valorConsumo, "Valor de consumo obligatorio");
        Objects.requireNonNull(unidadMedida, "Unidad de medida obligatoria");
        Objects.requireNonNull(fechaLectura, "Fecha obligatoria");

        var fechaActual = LocalDateTime.now();
        if (valorConsumo.compareTo(BigDecimal.ZERO) <= 0) {
        //if (valorConsumo.signum() <= 0) { Realiza lo mismo que compareTo pero es menos intuitivo.
            throw new IllegalArgumentException("El valor debe ser mayor a 0");
        }
        if (fechaLectura.isAfter(fechaActual)) {
            throw new IllegalArgumentException("Fecha incorrecta / La lectura no puede ser futura");
        }

    }
}
