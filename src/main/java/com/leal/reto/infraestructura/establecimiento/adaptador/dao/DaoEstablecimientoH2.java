package com.leal.reto.infraestructura.establecimiento.adaptador.dao;

import com.leal.reto.dominio.establecimiento.entidad.Establecimiento;
import com.leal.reto.dominio.establecimiento.puerto.DaoEstablecimiento;
import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DaoEstablecimientoH2 implements DaoEstablecimiento {

    private static final String ESTABLECIMIENTO_NO_ENCONTRADO = "Establecimiento no encontrado";

    private final JdbcTemplate jdbcTemplate;

    public DaoEstablecimientoH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Establecimiento consultarPorId(int idEstablecimiento) throws ExcepcionDeProceso {
        String sql = "select id, nit, nombre, puntosDisponibles, valorPunto from establecimiento where id = ?";
        return this.jdbcTemplate.query(sql, new MapeoEstablecimiento(), idEstablecimiento)
                .stream().findFirst().orElseThrow(() -> new ExcepcionDeProceso(ESTABLECIMIENTO_NO_ENCONTRADO));
    }
}