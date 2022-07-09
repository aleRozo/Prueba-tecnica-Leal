package com.leal.reto.infraestructura.usuario.adaptador.dao;

import com.leal.reto.dominio.usuario.entidad.Usuario;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoUsuario implements RowMapper<Usuario> {

    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Usuario(
                rs.getInt("id"),
                rs.getString("identificacion"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getInt("puntos")
        );
    }
}