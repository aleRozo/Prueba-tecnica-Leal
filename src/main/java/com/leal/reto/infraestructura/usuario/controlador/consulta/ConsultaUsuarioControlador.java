package com.leal.reto.infraestructura.usuario.controlador.consulta;

import com.leal.reto.aplicacion.usuario.consulta.ManejadorConsultaPuntos;
import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.usuario.dto.DtoUsuario;
import com.leal.reto.infraestructura.dto.RespuestaError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuario")
public class ConsultaUsuarioControlador {

    private final ManejadorConsultaPuntos manejadorConsultaPuntos;

    public ConsultaUsuarioControlador(ManejadorConsultaPuntos manejadorConsultaPuntos) {
        this.manejadorConsultaPuntos = manejadorConsultaPuntos;
    }

    @GetMapping(value = "/{identificacion}")
    public ResponseEntity<Object> consultarPuntos(@PathVariable String identificacion) {
        try {
            DtoUsuario usuario =  manejadorConsultaPuntos.consultaPuntos(identificacion);
            return ResponseEntity.status(HttpStatus.OK).body(usuario);
        } catch (ExcepcionDeProceso e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .body(new RespuestaError(e.getMessage(), HttpStatus.NOT_ACCEPTABLE.value()));
        }
    }
}
