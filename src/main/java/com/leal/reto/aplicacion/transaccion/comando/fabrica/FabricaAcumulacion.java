package com.leal.reto.aplicacion.transaccion.comando.fabrica;

import com.leal.reto.aplicacion.transaccion.comando.ComandoTransaccion;
import com.leal.reto.dominio.excepcion.ExcepcionValorInvalido;
import com.leal.reto.dominio.transaccion.entidad.Transaccion;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class FabricaAcumulacion {

    public Transaccion ejecutar(ComandoTransaccion comandoTransaccion) throws ExcepcionValorInvalido {
        return new Transaccion(
                LocalDateTime.now(),
                comandoTransaccion.getValorCompra(),
                comandoTransaccion.getIdUsuario(),
                comandoTransaccion.getIdEstablecimiento()
        );
    }
}
