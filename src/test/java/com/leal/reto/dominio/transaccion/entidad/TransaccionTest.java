package com.leal.reto.dominio.transaccion.entidad;

import com.leal.reto.dominio.excepcion.ExcepcionValorInvalido;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class TransaccionTest {

    private static final String VALOR_INVALIDO = "El valor de la compra debe ser mayor a cero";
    private static final String PUNTOS_INVALIDO = "Los puntos a redimir deben ser mayores a cero";
    private static final String ID_USUARIO_INVALIDO = "Debe ingresar un valor positivo en el ID del usuario";
    private static final String ID_ESTABLECIMIENTO_INVALIDO = "Debe ingresar un valor positivo en el ID del establecimiento";
    private static final String CAMPO_OBLIGATORIO = "Debe ingresar un valor para el campo %s";

    @Test
    public void transaccionValorObligatorioTest() {
        Float valor = null;
        try {
            new Transaccion(LocalDateTime.now(), valor, 1, 1);
        } catch (ExcepcionValorInvalido excepcionValorInvalido) {
            Assertions.assertEquals(String.format(CAMPO_OBLIGATORIO, "valor compra"), excepcionValorInvalido.getMessage());
        }
    }

    @Test
    public void transaccionIdUsuarioObligatorioTest() {
        try {
            new Transaccion(LocalDateTime.now(), 1F, null, 1);
        } catch (ExcepcionValorInvalido excepcionValorInvalido) {
            Assertions.assertEquals(String.format(CAMPO_OBLIGATORIO, "ID usuario"), excepcionValorInvalido.getMessage());
        }
    }

    @Test
    public void transaccionIdEstablecimientoObligatorioTest() {
        try {
            new Transaccion(LocalDateTime.now(), 1F, 1, null);
        } catch (ExcepcionValorInvalido excepcionValorInvalido) {
            Assertions.assertEquals(String.format(CAMPO_OBLIGATORIO, "ID establecimiento"), excepcionValorInvalido.getMessage());
        }
    }

    @Test
    public void transaccionValorMayorACeroTest() {
        try {
            new Transaccion(LocalDateTime.now(), 0f, 1, 1);
        } catch (ExcepcionValorInvalido excepcionValorInvalido) {
            Assertions.assertEquals(VALOR_INVALIDO, excepcionValorInvalido.getMessage());
        }
    }

    @Test
    public void transaccionIdUsuarioMayorACeroTest() {
        try {
            new Transaccion(LocalDateTime.now(), 1f, 0, 1);
        } catch (ExcepcionValorInvalido excepcionValorInvalido) {
            Assertions.assertEquals(ID_USUARIO_INVALIDO, excepcionValorInvalido.getMessage());
        }
    }

    @Test
    public void transaccionIdEstablecimientoMayorACeroTest() {
        try {
            new Transaccion(LocalDateTime.now(), 1f, 1, 0);
        } catch (ExcepcionValorInvalido excepcionValorInvalido) {
            Assertions.assertEquals(ID_ESTABLECIMIENTO_INVALIDO, excepcionValorInvalido.getMessage());
        }
    }

    @Test
    public void transaccionPuntosMayorACeroTest() {
        try {
            new Transaccion(LocalDateTime.now(), 0, 1, 1);
        } catch (ExcepcionValorInvalido excepcionValorInvalido) {
            Assertions.assertEquals(PUNTOS_INVALIDO, excepcionValorInvalido.getMessage());
        }
    }
}