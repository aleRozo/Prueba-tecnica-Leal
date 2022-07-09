package com.leal.reto.infraestructura.parametros.adaptador.dao;

import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.parametros.entidad.Parametro;
import com.leal.reto.dominio.parametros.puerto.DaoParametro;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DaoParametroH2 implements DaoParametro {

    private static final String PARAMETRO_NO_ENCONTRADO = "El parametro no fue encontrado";

    private final JdbcTemplate jdbcTemplate;

    public DaoParametroH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Parametro consultarPorCodigo(String codigo) throws ExcepcionDeProceso {
        String sql = "select id, codigo, valor from parametro where codigo = ?";
        return this.jdbcTemplate.query(sql, new MapeoParametro(), codigo)
                .stream().findFirst().orElseThrow(() -> new ExcepcionDeProceso(PARAMETRO_NO_ENCONTRADO));
    }
}
