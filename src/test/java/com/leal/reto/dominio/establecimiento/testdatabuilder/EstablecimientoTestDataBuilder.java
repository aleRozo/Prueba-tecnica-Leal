package com.leal.reto.dominio.establecimiento.testdatabuilder;

import com.leal.reto.dominio.establecimiento.entidad.Establecimiento;

public class EstablecimientoTestDataBuilder {

    private int id;
    private String nit;
    private String nombre;
    private int puntosDisponibles;
    private float valorPunto;

    public EstablecimientoTestDataBuilder() {
        this.id = 1;
        this.nit = "nit";
        this.nombre = "nombre";
        this.puntosDisponibles = 100;
        this.valorPunto = 1000;
    }

    public Establecimiento build() {
        return new Establecimiento(id, nit, nombre, puntosDisponibles, valorPunto);
    }
}
