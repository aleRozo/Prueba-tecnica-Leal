package com.leal.reto.dominio.establecimiento.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Establecimiento {
    private int id;
    private String nit;
    private String nombre;
    private int puntosDisponibles;
    private float valorPunto;
}
