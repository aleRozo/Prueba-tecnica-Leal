package com.leal.reto.infraestructura.tipotransaccion.adaptador.dao;

import com.leal.reto.dominio.tipotransaccion.entidad.TipoTransaccion;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoTipoTransaccion implements RowMapper<TipoTransaccion> {

    @Override
    public TipoTransaccion mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new TipoTransaccion(
                rs.getInt("id"),
                rs.getString("codigo"),
                rs.getString("nombre")
        );
    }
}