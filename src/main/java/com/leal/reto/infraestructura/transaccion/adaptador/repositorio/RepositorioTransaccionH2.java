package com.leal.reto.infraestructura.transaccion.adaptador.repositorio;

import com.leal.reto.dominio.transaccion.entidad.Transaccion;
import com.leal.reto.dominio.transaccion.puerto.RepositorioTransaccion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioTransaccionH2 implements RepositorioTransaccion {

    private final JdbcTemplate jdbcTemplate;

    public RepositorioTransaccionH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void crearTransaccion(Transaccion transaccion) {
        String sql = "insert into transaccion (fecha, valor, puntos, estado, idUsuario, idEstablecimiento, idTipoTransaccion) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                transaccion.getFecha(),
                transaccion.getValor(),
                transaccion.getPuntos(),
                transaccion.getEstado(),
                transaccion.getIdUsuario(),
                transaccion.getIdEstablecimiento(),
                transaccion.getIdTipoTransaccion());
    }
}