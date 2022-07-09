package com.leal.reto.aplicacion.transaccion.comando.manejador;

import com.leal.reto.aplicacion.transaccion.comando.ComandoTransaccion;
import com.leal.reto.aplicacion.transaccion.comando.fabrica.FabricaAcumulacion;
import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.excepcion.ExcepcionValorInvalido;
import com.leal.reto.dominio.transaccion.dto.DtoTransaccionAcumulacion;
import com.leal.reto.dominio.transaccion.entidad.Transaccion;
import com.leal.reto.dominio.transaccion.servicio.ServicioAcumulacion;
import org.springframework.stereotype.Component;

@Component
public class ManejadorAcumulacion {

    private final FabricaAcumulacion fabricaAcumulacion;
    private final ServicioAcumulacion servicioAcumulacion;

    public ManejadorAcumulacion(FabricaAcumulacion fabricaAcumulacion, ServicioAcumulacion servicioAcumulacion) {
        this.fabricaAcumulacion = fabricaAcumulacion;
        this.servicioAcumulacion = servicioAcumulacion;
    }

    public DtoTransaccionAcumulacion ejecutar(ComandoTransaccion comandoTransaccion)
            throws ExcepcionValorInvalido, ExcepcionDeProceso {
        Transaccion transaccion = this.fabricaAcumulacion.ejecutar(comandoTransaccion);
        return servicioAcumulacion.ejecutar(transaccion);
    }
}
