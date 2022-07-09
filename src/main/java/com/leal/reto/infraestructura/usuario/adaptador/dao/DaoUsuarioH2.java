package com.leal.reto.infraestructura.usuario.adaptador.dao;

import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.usuario.entidad.Usuario;
import com.leal.reto.dominio.usuario.puerto.DaoUsuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DaoUsuarioH2 implements DaoUsuario {

    private static final String USUARIO_NO_ENCONTRADO = "Usuario no encontrado";

    private final JdbcTemplate jdbcTemplate;

    public DaoUsuarioH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Usuario consultarPuntosPorIdentificacion(String identificacion) throws ExcepcionDeProceso {
        String sql = "select id, identificacion, nombre, apellido, puntos from usuario where identificacion = ?";
        return this.jdbcTemplate.query(sql, new MapeoUsuario(), identificacion)
                .stream().findFirst().orElseThrow(() -> new ExcepcionDeProceso(USUARIO_NO_ENCONTRADO));
    }

    @Override
    public Usuario consultarPuntosPorId(int idUsuario) throws ExcepcionDeProceso {
        String sql = "select id, identificacion, nombre, apellido, puntos from usuario where id = ?";
        return this.jdbcTemplate.query(sql, new MapeoUsuario(), idUsuario)
                .stream().findFirst().orElseThrow(() -> new ExcepcionDeProceso(USUARIO_NO_ENCONTRADO));
    }
}