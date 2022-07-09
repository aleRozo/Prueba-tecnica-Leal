package com.leal.reto.dominio.parametros.puerto;

import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.parametros.entidad.Parametro;

public interface DaoParametro {

    Parametro consultarPorCodigo(String codigo) throws ExcepcionDeProceso;
}
