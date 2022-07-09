package com.leal.reto.infraestructura.usuario.adaptador.repositorio;

import com.leal.reto.dominio.usuario.entidad.Usuario;
import com.leal.reto.dominio.usuario.puerto.RepositorioUsuario;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioUsuarioH2 implements RepositorioUsuario {

    private final JdbcTemplate jdbcTemplate;

    public RepositorioUsuarioH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void actualizarPuntos(Usuario usuario) {
        String sql = "update usuario set puntos = ? where id = ?";
        jdbcTemplate.update(sql, usuario.getPuntos(), usuario.getId());
    }
}
