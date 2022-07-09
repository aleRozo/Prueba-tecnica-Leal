package com.leal.reto.aplicacion.transaccion.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ComandoTransaccion {

    private Integer idUsuario;
    private Integer idEstablecimiento;
    private Float valorCompra;
    private Integer puntosRedimir;

}
