package com.leal.reto.dominio.transaccion.dto;

import lombok.Data;

@Data
public class DtoTransaccionAcumulacion {
    private String usuario;
    private String establecimiento;
    private Float valorCompra;
    private Integer puntosAcumulados;}
