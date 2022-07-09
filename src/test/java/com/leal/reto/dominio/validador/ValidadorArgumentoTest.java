package com.leal.reto.dominio.validador;

import com.leal.reto.dominio.excepcion.ExcepcionValorInvalido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidadorArgumentoTest {
    private static final String MENSAJE_ERROR = "Mensaje de error";

    @Test
    public void validarMayorACeroFloatTest() {
        try {
            ValidadorArgumento.validarMayorACero(0f, MENSAJE_ERROR);
        } catch (ExcepcionValorInvalido excepcionValorInvalido) {
            Assertions.assertEquals(MENSAJE_ERROR, excepcionValorInvalido.getMessage());
        }
    }

    @Test
    public void validarMayorACeroIntegerTest() {
        try {
            ValidadorArgumento.validarMayorACero(0, MENSAJE_ERROR);
        } catch (ExcepcionValorInvalido excepcionValorInvalido) {
            Assertions.assertEquals(MENSAJE_ERROR, excepcionValorInvalido.getMessage());
        }
    }

    @Test
    public void validarObligatorioTest() {
        try {
            ValidadorArgumento.validarObligatorio(null, MENSAJE_ERROR);
        } catch (ExcepcionValorInvalido excepcionValorInvalido) {
            Assertions.assertEquals(MENSAJE_ERROR, excepcionValorInvalido.getMessage());
        }
    }
}
