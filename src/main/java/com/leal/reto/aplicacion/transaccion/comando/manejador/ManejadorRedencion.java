package com.leal.reto.aplicacion.transaccion.comando.manejador;

import com.leal.reto.aplicacion.transaccion.comando.ComandoTransaccion;
import com.leal.reto.aplicacion.transaccion.comando.fabrica.FabricaRedencion;
import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.excepcion.ExcepcionValorInvalido;
import com.leal.reto.dominio.transaccion.dto.DtoTransaccionRedencion;
import com.leal.reto.dominio.transaccion.entidad.Transaccion;
import com.leal.reto.dominio.transaccion.servicio.ServicioRedencion;
import org.springframework.stereotype.Component;

@Component
public class ManejadorRedencion {

    private final FabricaRedencion fabricaRedencion;
    private final ServicioRedencion servicioRedencion;

    public ManejadorRedencion(FabricaRedencion fabricaRedencion, ServicioRedencion servicioRedencion) {
        this.fabricaRedencion = fabricaRedencion;
        this.servicioRedencion = servicioRedencion;
    }

    public DtoTransaccionRedencion ejecutar(ComandoTransaccion comandoTransaccion) throws ExcepcionValorInvalido, ExcepcionDeProceso {
        Transaccion transaccion = this.fabricaRedencion.ejecutar(comandoTransaccion);
        return servicioRedencion.ejecutar(transaccion);
    }
}
