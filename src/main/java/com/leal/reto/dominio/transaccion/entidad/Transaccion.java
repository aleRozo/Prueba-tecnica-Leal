package com.leal.reto.dominio.transaccion.entidad;

import com.leal.reto.dominio.excepcion.ExcepcionValorInvalido;
import com.leal.reto.dominio.validador.ValidadorArgumento;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

public class Transaccion {

    private static final String VALOR_INVALIDO = "El valor de la compra debe ser mayor a cero";
    private static final String PUNTOS_INVALIDO = "Los puntos a redimir deben ser mayores a cero";
    private static final String ID_USUARIO_INVALIDO = "Debe ingresar un valor positivo en el ID del usuario";
    private static final String ID_ESTABLECIMIENTO_INVALIDO = "Debe ingresar un valor positivo en el ID del establecimiento";
    private static final String CAMPO_OBLIGATORIO = "Debe ingresar un valor para el campo %s";

    private Integer id;
    @Getter
    private LocalDateTime fecha;
    @Getter
    private Float valor;
    @Setter
    @Getter
    private Integer puntos;
    @Setter
    @Getter
    private String estado;
    @Getter
    private Integer idUsuario;
    @Getter
    private Integer idEstablecimiento;
    @Setter
    @Getter
    private Integer idTipoTransaccion;

    public Transaccion(LocalDateTime fecha, Float valor, Integer idUsuario, Integer idEstablecimiento) throws ExcepcionValorInvalido {
        ValidadorArgumento.validarObligatorio(valor, String.format(CAMPO_OBLIGATORIO, "valor compra"));
        ValidadorArgumento.validarObligatorio(idUsuario, String.format(CAMPO_OBLIGATORIO, "ID usuario"));
        ValidadorArgumento.validarObligatorio(idEstablecimiento, String.format(CAMPO_OBLIGATORIO, "ID establecimiento"));
        ValidadorArgumento.validarMayorACero(valor, VALOR_INVALIDO);
        ValidadorArgumento.validarMayorACero(idUsuario, ID_USUARIO_INVALIDO);
        ValidadorArgumento.validarMayorACero(idEstablecimiento, ID_ESTABLECIMIENTO_INVALIDO);
        
        this.fecha = fecha;
        this.valor = valor;
        this.idUsuario = idUsuario;
        this.idEstablecimiento = idEstablecimiento;
    }

    public Transaccion(LocalDateTime fecha, Integer puntos, Integer idUsuario, Integer idEstablecimiento) throws ExcepcionValorInvalido {
        ValidadorArgumento.validarObligatorio(puntos, String.format(CAMPO_OBLIGATORIO, "puntos"));
        ValidadorArgumento.validarObligatorio(idUsuario, String.format(CAMPO_OBLIGATORIO, "ID usuario"));
        ValidadorArgumento.validarObligatorio(idEstablecimiento, String.format(CAMPO_OBLIGATORIO, "ID establecimiento"));
        ValidadorArgumento.validarMayorACero(puntos, PUNTOS_INVALIDO);
        ValidadorArgumento.validarMayorACero(idUsuario, ID_USUARIO_INVALIDO);
        ValidadorArgumento.validarMayorACero(idEstablecimiento, ID_ESTABLECIMIENTO_INVALIDO);

        this.fecha = fecha;
        this.puntos = puntos;
        this.idUsuario = idUsuario;
        this.idEstablecimiento = idEstablecimiento;
    }
}
