package com.leal.reto.infraestructura.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RespuestaError {
    private String mensaje;
    private int status;
}
