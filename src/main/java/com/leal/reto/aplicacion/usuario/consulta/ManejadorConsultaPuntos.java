package com.leal.reto.aplicacion.usuario.consulta;

import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.usuario.dto.DtoUsuario;
import com.leal.reto.dominio.usuario.entidad.Usuario;
import com.leal.reto.dominio.usuario.puerto.DaoUsuario;
import org.springframework.stereotype.Component;

@Component
public class ManejadorConsultaPuntos {

    private final DaoUsuario daoUsuario;

    public ManejadorConsultaPuntos(DaoUsuario daoUsuario) {
        this.daoUsuario = daoUsuario;
    }

    public DtoUsuario consultaPuntos(String identificacion) throws ExcepcionDeProceso {
        Usuario usuario =  daoUsuario.consultarPuntosPorIdentificacion(identificacion);
        return new DtoUsuario(
                usuario.getId(),
                usuario.getIdentificacion(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getPuntos()
        );
    }
}
