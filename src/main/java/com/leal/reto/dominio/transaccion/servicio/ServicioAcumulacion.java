package com.leal.reto.dominio.transaccion.servicio;

import com.leal.reto.dominio.establecimiento.entidad.Establecimiento;
import com.leal.reto.dominio.establecimiento.puerto.RepositorioEstablecimiento;
import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.transaccion.dto.DtoTransaccionAcumulacion;
import com.leal.reto.dominio.transaccion.entidad.Transaccion;
import com.leal.reto.dominio.transaccion.puerto.RepositorioTransaccion;
import com.leal.reto.dominio.usuario.entidad.Usuario;
import com.leal.reto.dominio.usuario.puerto.RepositorioUsuario;

public class ServicioAcumulacion {

    private static final String CODIGO_ACUMULACION = "ACUMULACION";
    private static final String ESTADO_EXITOSO = "EXITOSO";
    private static final String ESTADO_FALLIDO = "FALLIDO";
    private static final String EL_ESTABLECIMIENTO_NO_CUENTA_CON_PUNTOS_DISPONIBLES_PARA_ACUMULAR = "El establecimiento no cuenta con puntos disponibles para acumular";

    private final RepositorioEstablecimiento repositorioEstablecimiento;
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioTransaccion repositorioTransaccion;
    private final ServicioValidarTransaccion servicioValidarTransaccion;

    public ServicioAcumulacion(
            RepositorioEstablecimiento repositorioEstablecimiento,
            RepositorioUsuario repositorioUsuario,
            RepositorioTransaccion repositorioTransaccion,
            ServicioValidarTransaccion servicioValidarTransaccion) {
        this.servicioValidarTransaccion = servicioValidarTransaccion;
        this.repositorioEstablecimiento = repositorioEstablecimiento;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioTransaccion = repositorioTransaccion;
    }

    public DtoTransaccionAcumulacion ejecutar(Transaccion transaccion) throws ExcepcionDeProceso {
        Usuario usuario = servicioValidarTransaccion.consultarUsuario(transaccion);
        Establecimiento establecimiento = servicioValidarTransaccion.consultarEstablecimiento(transaccion);
        transaccion.setIdTipoTransaccion(servicioValidarTransaccion.consultarTipoTransaccion(CODIGO_ACUMULACION));
        transaccion.setPuntos(obtenerPuntosAcumulados(establecimiento.getValorPunto(), transaccion.getValor()));
        actualizarPuntosEstablecimiento(establecimiento, transaccion);
        actualizarPuntosUsuario(usuario, transaccion);
        transaccion.setEstado(ESTADO_EXITOSO);
        repositorioTransaccion.crearTransaccion(transaccion);
        return obtenerRespuesta(establecimiento, usuario, transaccion);
    }

    private int obtenerPuntosAcumulados(float valorPunto, float valorCompra) {
        return (int)Math.floor(valorCompra/valorPunto);
    }

    private void actualizarPuntosEstablecimiento(Establecimiento establecimiento, Transaccion transaccion) throws ExcepcionDeProceso {
        int puntosEstablecimiento = establecimiento.getPuntosDisponibles();
        int puntosAcumulados = transaccion.getPuntos();
        if(puntosEstablecimiento >= puntosAcumulados) {
            establecimiento.setPuntosDisponibles(puntosEstablecimiento - puntosAcumulados);
            repositorioEstablecimiento.actualizarPuntosDisponibles(establecimiento);
        } else {
            transaccion.setEstado(ESTADO_FALLIDO);
            repositorioTransaccion.crearTransaccion(transaccion);
            throw new ExcepcionDeProceso(EL_ESTABLECIMIENTO_NO_CUENTA_CON_PUNTOS_DISPONIBLES_PARA_ACUMULAR);
        }
    }

    private void actualizarPuntosUsuario(Usuario usuario, Transaccion transaccion) {
        int puntosUsuario = usuario.getPuntos();
        int puntosAcumulados = transaccion.getPuntos();
        usuario.setPuntos(puntosUsuario + puntosAcumulados);
        repositorioUsuario.actualizarPuntos(usuario);
    }

    private DtoTransaccionAcumulacion obtenerRespuesta(Establecimiento establecimiento, Usuario usuario, Transaccion transaccion) {
        DtoTransaccionAcumulacion dtoTransaccionAcumulacion = new DtoTransaccionAcumulacion();
        dtoTransaccionAcumulacion.setUsuario(usuario.getNombre() + " " + usuario.getApellido());
        dtoTransaccionAcumulacion.setEstablecimiento(establecimiento.getNombre());
        dtoTransaccionAcumulacion.setValorCompra(transaccion.getValor());
        dtoTransaccionAcumulacion.setPuntosAcumulados(transaccion.getPuntos());
        return dtoTransaccionAcumulacion;
    }
}
