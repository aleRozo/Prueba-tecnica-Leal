package com.leal.reto.infraestructura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RespuestaExitosa <T> {
    private T datos;
    private int status;
}
