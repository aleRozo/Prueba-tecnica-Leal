package com.leal.reto.infraestructura.parametros.adaptador.dao;

import com.leal.reto.dominio.parametros.entidad.Parametro;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoParametro implements RowMapper<Parametro> {

    @Override
    public Parametro mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Parametro(
                rs.getInt("id"),
                rs.getString("codigo"),
                rs.getString("valor")
        );
    }
}
