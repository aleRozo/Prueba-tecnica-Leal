package com.leal.reto.dominio.transaccion.testdatabuilder;

import com.leal.reto.dominio.excepcion.ExcepcionValorInvalido;
import com.leal.reto.dominio.transaccion.entidad.Transaccion;

import java.time.LocalDateTime;

public class TransaccionTestDataBuilder {

    private Integer id;
    private LocalDateTime fecha;
    private Float valor;
    private Integer puntos;
    private String estado;
    private Integer idUsuario;
    private Integer idEstablecimiento;
    private Integer idTipoTransaccion;

    public TransaccionTestDataBuilder() {
        this.id = 1;
        this.fecha = LocalDateTime.now();
        this.valor = 1000f;
        this.puntos = 1;
        this.estado = "EXITOSO";
        this.idUsuario = 1;
        this.idEstablecimiento = 1;
        this.idTipoTransaccion = 1;
    }

    public TransaccionTestDataBuilder conValor(Float valor) {
        this.valor = valor;
        return this;
    }

    public TransaccionTestDataBuilder conPuntos(int puntos) {
        this.puntos = puntos;
        return this;
    }

    public Transaccion buildAcumulacion() throws ExcepcionValorInvalido {
        return new Transaccion(fecha, valor, idUsuario, idEstablecimiento);
    }

    public Transaccion buildRedencion() throws ExcepcionValorInvalido {
        return new Transaccion(fecha, puntos, idUsuario, idEstablecimiento);
    }
}
