package com.leal.reto.dominio.transaccion.servicio;

import com.leal.reto.dominio.establecimiento.entidad.Establecimiento;
import com.leal.reto.dominio.establecimiento.puerto.DaoEstablecimiento;
import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.tipotransaccion.puerto.DaoTipoTransaccion;
import com.leal.reto.dominio.transaccion.entidad.Transaccion;
import com.leal.reto.dominio.usuario.entidad.Usuario;
import com.leal.reto.dominio.usuario.puerto.DaoUsuario;

public class ServicioValidarTransaccion {
    private static final String USUARIO_NO_ENCONTRADO = "El usuario que intenta realizar la acumulación no existe";
    private static final String TIPO_TRANSACCION_NO_ENCONTRADO = "El tipo de transaccion acumulación no existe";
    private static final String ESTABLECIMIENTO_NO_ENCONTRADO = "El establecimiento en el que intenta realizar la acumulación no existe";

    private final DaoUsuario daoUsuario;
    private final DaoEstablecimiento daoEstablecimiento;
    private final DaoTipoTransaccion daoTipoTransaccion;

    public ServicioValidarTransaccion(DaoUsuario daoUsuario, DaoEstablecimiento daoEstablecimiento, DaoTipoTransaccion daoTipoTransaccion) {
        this.daoUsuario = daoUsuario;
        this.daoEstablecimiento = daoEstablecimiento;
        this.daoTipoTransaccion = daoTipoTransaccion;
    }

    public Usuario consultarUsuario(Transaccion transaccion) throws ExcepcionDeProceso {
        try {
            return daoUsuario.consultarPuntosPorId(transaccion.getIdUsuario());
        } catch (ExcepcionDeProceso excepcionDeProceso) {
            throw new ExcepcionDeProceso(USUARIO_NO_ENCONTRADO);
        }
    }

    public int consultarTipoTransaccion(String codigo) throws ExcepcionDeProceso {
        try {
            return daoTipoTransaccion.consultarPorCodigo(codigo).getId();
        } catch (ExcepcionDeProceso excepcionDeProceso) {
            throw new ExcepcionDeProceso(TIPO_TRANSACCION_NO_ENCONTRADO);
        }
    }

    public Establecimiento consultarEstablecimiento(Transaccion transaccion) throws ExcepcionDeProceso {
        try {
            return daoEstablecimiento.consultarPorId(transaccion.getIdEstablecimiento());
        } catch (ExcepcionDeProceso excepcionDeProceso) {
            throw new ExcepcionDeProceso(ESTABLECIMIENTO_NO_ENCONTRADO);
        }
    }


}
