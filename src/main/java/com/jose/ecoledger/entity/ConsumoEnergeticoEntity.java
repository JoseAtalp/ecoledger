package com.jose.ecoledger.entity;

import com.jose.ecoledger.domain.UnidadMedida;
// Un mapper es el puente que convierte una Entity en un DTO (para devolver los datos al cliente
// sin exponer detalles internos de la base de datos) y viceversa.

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name="consumos_energeticos")
public class ConsumoEnergeticoEntity {

    // Columna id
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    // Columna valorConsumo (Como se mapea el campo de la clase hacia la col de la BBDD)
    // Pon @Column cuando quieras cambiar algo del comportamiento por defecto.
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal valorConsumo;

    // Columna con el valor del Enum UnidadMedida.java
    // Ventaja: Es mucho más legible directamente en la base de datos y es inmune a que
    // cambies el orden de los elementos en el código Java.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UnidadMedida unidadMedida;

    @Column(nullable = false)
    private LocalDateTime fechaLectura;

    // Requerido por Hibernate para crear el objeto antes de rellenarlo por reflexión al leer de la BD.
    // No es público para evitar que el código cree consumos vacíos (inválidos).
    protected ConsumoEnergeticoEntity(){

    }
    //CONSTRUCTOR
    public ConsumoEnergeticoEntity(BigDecimal valorConsumo, UnidadMedida unidadMedida,  LocalDateTime fechaLectura ){
        this.valorConsumo = valorConsumo;
        this.unidadMedida = unidadMedida;
        this.fechaLectura = fechaLectura;
    }
    //GETTERS
    public Long getId(){
        return this.id;
    }
    public BigDecimal getValorConsumo(){
        return this.valorConsumo;
    }
    public UnidadMedida getUnidadMedida(){
        return this.unidadMedida;
    }
    public LocalDateTime getFechaLectura(){
        return this.fechaLectura;
    }
}
