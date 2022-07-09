package com.leal.reto.infraestructura.establecimiento.adaptador.dao;

import com.leal.reto.dominio.establecimiento.entidad.Establecimiento;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoEstablecimiento implements RowMapper<Establecimiento> {

    @Override
    public Establecimiento mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Establecimiento(
                rs.getInt("id"),
                rs.getString("nit"),
                rs.getString("nombre"),
                rs.getInt("puntosDisponibles"),
                rs.getFloat("valorPunto")
        );
    }
}
