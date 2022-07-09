package com.leal.reto.dominio.parametro.testdatabuilder;

import com.leal.reto.dominio.parametros.entidad.Parametro;

public class ParametroTestDataBuilder {
    private int id;
    private String codigo;
    private String valor;

    public ParametroTestDataBuilder() {
        this.id = 1;
        this.codigo = "PUNTOS_MINIMOS_PARA_REDIMIR";
        this.valor = "10";
    }

    public Parametro build() {
        return new Parametro(id, codigo, valor);
    }
}
