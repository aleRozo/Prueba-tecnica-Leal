package com.leal.reto.infraestructura.transaccion.controlador.comando.testdatabuilder;

import com.leal.reto.aplicacion.transaccion.comando.ComandoTransaccion;

public class ComandoTransaccionTestDataBuilder {

    private Integer idUsuario;
    private Integer idEstablecimiento;
    private Float valorCompra;
    private Integer puntosRedimir;

    public ComandoTransaccionTestDataBuilder() {
        this.idUsuario = 1;
        this.idEstablecimiento = 1;
        this.valorCompra = 10000f;
        this.puntosRedimir = 10;
    }

    public ComandoTransaccionTestDataBuilder conPuntosRedimir(Integer puntosRedimir) {
        this.puntosRedimir = puntosRedimir;
        return this;
    }

    public ComandoTransaccionTestDataBuilder conIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }

    public ComandoTransaccionTestDataBuilder conIdEstablecimiento(Integer idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
        return this;
    }

    public ComandoTransaccionTestDataBuilder conValorCompra(Float valorCompra) {
        this.valorCompra = valorCompra;
        return this;
    }

    public ComandoTransaccion build() {
        return new ComandoTransaccion(idUsuario, idEstablecimiento, valorCompra, puntosRedimir);
    }
}
