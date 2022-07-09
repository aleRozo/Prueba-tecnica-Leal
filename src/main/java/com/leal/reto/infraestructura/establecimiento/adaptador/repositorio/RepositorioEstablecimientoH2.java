package com.leal.reto.infraestructura.establecimiento.adaptador.repositorio;

import com.leal.reto.dominio.establecimiento.entidad.Establecimiento;
import com.leal.reto.dominio.establecimiento.puerto.RepositorioEstablecimiento;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioEstablecimientoH2 implements RepositorioEstablecimiento {

    private final JdbcTemplate jdbcTemplate;

    public RepositorioEstablecimientoH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void actualizarPuntosDisponibles(Establecimiento establecimiento) {
        String sql = "update establecimiento set puntosDisponibles = ? where id = ?";
        jdbcTemplate.update(sql, establecimiento.getPuntosDisponibles(), establecimiento.getId());
    }
}