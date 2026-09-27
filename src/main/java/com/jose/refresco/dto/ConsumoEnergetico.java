package com.jose.refresco.dto;
import java.time.LocalDateTime;
import java.util.Objects;

//Se suelen crear 2 dto distintos, uno para "crear/comprobar"
//y otro para leer (por ejemplo la id una vez devuelta)

public record ConsumoEnergetico (double valorConsumo,
                                 UnidadMedida unidadMedida ,
                                 LocalDateTime fechaLectura) {

    public ConsumoEnergetico {
        Objects.requireNonNull(unidadMedida, "Unidad de medida obligatoria");
        Objects.requireNonNull(fechaLectura, "Fecha incorrecta / La lectura es null");
        var fechaActual = LocalDateTime.now(); //Fecha
            if (valorConsumo  <= 0 ){
                throw new IllegalArgumentException ("Valores erroneos");
            }
            if (fechaLectura.isAfter(fechaActual)) {
                    throw new IllegalArgumentException ("Fecha incorrecta / La lectura no puede ser futura");
            }

    }
}
