package com.leal.reto.dominio.transaccion.puerto;

import com.leal.reto.dominio.transaccion.entidad.Transaccion;

public interface RepositorioTransaccion {

    void crearTransaccion(Transaccion transaccion);
}
