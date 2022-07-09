package com.leal.reto.dominio.usuario.puerto;

import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.usuario.entidad.Usuario;

public interface DaoUsuario {
    Usuario consultarPuntosPorIdentificacion(String identificacion) throws ExcepcionDeProceso;

    Usuario consultarPuntosPorId(int idUsuario) throws ExcepcionDeProceso;
}
