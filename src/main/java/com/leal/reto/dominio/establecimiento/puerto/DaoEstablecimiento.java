package com.leal.reto.dominio.establecimiento.puerto;

import com.leal.reto.dominio.establecimiento.entidad.Establecimiento;
import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;

public interface DaoEstablecimiento {

    Establecimiento consultarPorId(int idEstablecimiento) throws ExcepcionDeProceso;
}
