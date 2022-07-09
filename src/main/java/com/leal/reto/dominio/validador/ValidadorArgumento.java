package com.leal.reto.dominio.validador;

import com.leal.reto.dominio.excepcion.ExcepcionValorInvalido;

public class ValidadorArgumento {

    public static void validarMayorACero(Float valor, String mensaje) throws ExcepcionValorInvalido {
        if(valor <= 0f) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    public static void validarMayorACero(Integer valor, String mensaje) throws ExcepcionValorInvalido {
        if(valor <= 0) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }

    public static void validarObligatorio(Object valor, String mensaje) throws ExcepcionValorInvalido {
        if(valor == null) {
            throw new ExcepcionValorInvalido(mensaje);
        }
    }
}
