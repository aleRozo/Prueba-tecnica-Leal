package com.leal.reto.dominio.transaccion.dto;

import lombok.Data;

@Data
public class DtoTransaccionRedencion {
    private String usuario;
    private String establecimiento;
    private Integer puntosRedimidos;
}

