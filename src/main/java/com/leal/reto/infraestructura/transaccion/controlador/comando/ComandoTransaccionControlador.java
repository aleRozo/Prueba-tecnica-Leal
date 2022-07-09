package com.leal.reto.infraestructura.transaccion.controlador.comando;

import com.leal.reto.aplicacion.transaccion.comando.ComandoTransaccion;
import com.leal.reto.aplicacion.transaccion.comando.manejador.ManejadorAcumulacion;
import com.leal.reto.aplicacion.transaccion.comando.manejador.ManejadorRedencion;
import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.excepcion.ExcepcionValorInvalido;
import com.leal.reto.dominio.transaccion.dto.DtoTransaccionAcumulacion;
import com.leal.reto.dominio.transaccion.dto.DtoTransaccionRedencion;
import com.leal.reto.infraestructura.dto.RespuestaError;
import com.leal.reto.infraestructura.dto.RespuestaExitosa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/transaccion")
public class ComandoTransaccionControlador {

    private final ManejadorAcumulacion manejadorAcumulacion;
    private final ManejadorRedencion manejadorRedencion;

    public ComandoTransaccionControlador(
            ManejadorAcumulacion manejadorAcumulacion, ManejadorRedencion manejadorRedencion) {
        this.manejadorAcumulacion = manejadorAcumulacion;
        this.manejadorRedencion = manejadorRedencion;
    }

    @PostMapping(value = "/acumulacion")
    public ResponseEntity<Object> realizarAcumulacion(@RequestBody ComandoTransaccion comandoTransaccion) {
        try {
            DtoTransaccionAcumulacion respuesta = manejadorAcumulacion.ejecutar(comandoTransaccion);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new RespuestaExitosa<>(respuesta, HttpStatus.OK.value()));
        } catch (ExcepcionValorInvalido e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new RespuestaError(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        } catch (ExcepcionDeProceso e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new RespuestaError(e.getMessage(), HttpStatus.NOT_ACCEPTABLE.value()));
        }
    }

    @PostMapping(value = "/redencion")
    public ResponseEntity<Object> realizarRedencion(@RequestBody ComandoTransaccion comandoTransaccion) {
        try {
            DtoTransaccionRedencion respuesta = manejadorRedencion.ejecutar(comandoTransaccion);
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new RespuestaExitosa<>(respuesta, HttpStatus.OK.value()));
        } catch (ExcepcionValorInvalido e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new RespuestaError(e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        } catch (ExcepcionDeProceso e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new RespuestaError(e.getMessage(), HttpStatus.NOT_ACCEPTABLE.value()));
        }
    }
}
