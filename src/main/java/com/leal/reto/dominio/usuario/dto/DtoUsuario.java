package com.leal.reto.dominio.usuario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DtoUsuario {
    private int id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private int puntos;
}
