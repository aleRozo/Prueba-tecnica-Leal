package com.leal.reto.dominio.tipotransaccion.puerto;

import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.tipotransaccion.entidad.TipoTransaccion;

public interface DaoTipoTransaccion {

    TipoTransaccion consultarPorCodigo(String codigo) throws ExcepcionDeProceso;
}
