package com.leal.reto.dominio.tipotransaccion.testdatabuilder;

import com.leal.reto.dominio.tipotransaccion.entidad.TipoTransaccion;

public class TipoTransaccionTestDataBuilder {

    private int id;
    private String codigo;
    private String nombre;

    public TipoTransaccionTestDataBuilder() {
        this.id = 1;
        this.codigo = "codigo";
        this.nombre = "nombre";
    }

    public TipoTransaccionTestDataBuilder conCodigo(String codigo) {
        this.codigo = codigo;
        return this;
    }

    public TipoTransaccion build() {
        return new TipoTransaccion(id, codigo, nombre);
    }
}
