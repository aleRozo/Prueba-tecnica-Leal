package com.leal.reto.dominio.usuario.entidad;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Usuario {
    private int id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private int puntos;
}
