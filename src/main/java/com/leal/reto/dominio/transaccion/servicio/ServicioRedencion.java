package com.leal.reto.dominio.transaccion.servicio;

import com.leal.reto.dominio.establecimiento.entidad.Establecimiento;
import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.parametros.entidad.Parametro;
import com.leal.reto.dominio.parametros.puerto.DaoParametro;
import com.leal.reto.dominio.transaccion.dto.DtoTransaccionRedencion;
import com.leal.reto.dominio.transaccion.entidad.Transaccion;
import com.leal.reto.dominio.transaccion.puerto.RepositorioTransaccion;
import com.leal.reto.dominio.usuario.entidad.Usuario;
import com.leal.reto.dominio.usuario.puerto.RepositorioUsuario;

public class ServicioRedencion {

    private static final String CODIGO_REDENCION = "REDENCION";
    private static final String ESTADO_EXITOSO = "EXITOSO";
    private static final String ESTADO_FALLIDO = "FALLIDO";
    private static final String CODIGO_PARAMETRO_MINIMO_PARA_REDIMIR = "PUNTOS_MINIMOS_PARA_REDIMIR";
    private static final String EL_USUARIO_NO_TIENE_LA_CANTIDAD_MINIMA_DE_PUNTOS_PARA_PODER_REDIMIR =
            "El usuario no tiene la cantidad minima de puntos para poder redimir";
    private static final String EL_USUARIO_NO_TIENE_SUFICIENTES_PUNTOS_PARA_REDIMIR =
            "El usuario no tiene suficientes puntos para redimir";

    private final DaoParametro daoParametro;
    private final RepositorioUsuario repositorioUsuario;
    private final RepositorioTransaccion repositorioTransaccion;
    private final ServicioValidarTransaccion servicioValidarTransaccion;

    public ServicioRedencion(
            DaoParametro daoParametro,
            RepositorioUsuario repositorioUsuario,
            RepositorioTransaccion repositorioTransaccion,
            ServicioValidarTransaccion servicioValidarTransaccion) {
        this.daoParametro = daoParametro;
        this.repositorioUsuario = repositorioUsuario;
        this.repositorioTransaccion = repositorioTransaccion;
        this.servicioValidarTransaccion = servicioValidarTransaccion;
    }

    public DtoTransaccionRedencion ejecutar(Transaccion transaccion) throws ExcepcionDeProceso {
        Usuario usuario = servicioValidarTransaccion.consultarUsuario(transaccion);
        Establecimiento establecimiento = servicioValidarTransaccion.consultarEstablecimiento(transaccion);
        transaccion.setIdTipoTransaccion(servicioValidarTransaccion.consultarTipoTransaccion(CODIGO_REDENCION));
        transaccion.setPuntos(obtenerPuntosRedimidos(transaccion));
        actualizarPuntosUsuario(usuario, transaccion);
        transaccion.setEstado(ESTADO_EXITOSO);
        crearTransaccion(transaccion);
        return obtenerRespuesta(establecimiento, usuario, transaccion);
    }

    private int obtenerPuntosRedimidos(Transaccion transaccion) throws ExcepcionDeProceso {
        Parametro minimoParaRedimir = daoParametro.consultarPorCodigo(CODIGO_PARAMETRO_MINIMO_PARA_REDIMIR);
        int puntosRedimir = transaccion.getPuntos();
        int puntosMinimosParaRedimir = Integer.parseInt(minimoParaRedimir.getValor());
        if(puntosRedimir >= puntosMinimosParaRedimir) {
            return puntosRedimir;
        } else {
            transaccion.setEstado(ESTADO_FALLIDO);
            crearTransaccion(transaccion);
            throw new ExcepcionDeProceso(EL_USUARIO_NO_TIENE_LA_CANTIDAD_MINIMA_DE_PUNTOS_PARA_PODER_REDIMIR);
        }
    }

    private void actualizarPuntosUsuario(Usuario usuario, Transaccion transaccion) throws ExcepcionDeProceso {
        int puntosUsuario = usuario.getPuntos();
        int puntosRedimir = transaccion.getPuntos();
        int puntosRestantes = puntosUsuario - puntosRedimir;
        if(puntosRestantes >= 0) {
            usuario.setPuntos(puntosRestantes);
            repositorioUsuario.actualizarPuntos(usuario);
        } else {
            transaccion.setEstado(ESTADO_FALLIDO);
            crearTransaccion(transaccion);
            throw new ExcepcionDeProceso(EL_USUARIO_NO_TIENE_SUFICIENTES_PUNTOS_PARA_REDIMIR);
        }
    }

    private void crearTransaccion(Transaccion transaccion) {
        repositorioTransaccion.crearTransaccion(transaccion);
    }

    private DtoTransaccionRedencion obtenerRespuesta(Establecimiento establecimiento, Usuario usuario, Transaccion transaccion) {
        DtoTransaccionRedencion dtoTransaccionRedencion = new DtoTransaccionRedencion();
        dtoTransaccionRedencion.setUsuario(usuario.getNombre() + " " + usuario.getApellido());
        dtoTransaccionRedencion.setEstablecimiento(establecimiento.getNombre());
        dtoTransaccionRedencion.setPuntosRedimidos(transaccion.getPuntos());
        return dtoTransaccionRedencion;
    }

}
