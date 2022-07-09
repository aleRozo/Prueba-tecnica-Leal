package com.leal.reto.dominio.tipotransaccion.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TipoTransaccion {
    private int id;
    private String codigo;
    private String nombre;
}
