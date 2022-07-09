package com.leal.reto.dominio.parametros.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Parametro {
    private int id;
    private String codigo;
    private String valor;
}
