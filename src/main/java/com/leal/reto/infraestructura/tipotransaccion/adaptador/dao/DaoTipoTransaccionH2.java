package com.leal.reto.infraestructura.tipotransaccion.adaptador.dao;

import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.tipotransaccion.entidad.TipoTransaccion;
import com.leal.reto.dominio.tipotransaccion.puerto.DaoTipoTransaccion;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DaoTipoTransaccionH2 implements DaoTipoTransaccion {

    private static final String TIPO_TRANSACCION_NO_ENCONTRADA = "Tipo transaccion no encontrada";

    private final JdbcTemplate jdbcTemplate;

    public DaoTipoTransaccionH2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TipoTransaccion consultarPorCodigo(String codigo) throws ExcepcionDeProceso {
        String sql = "select id, codigo, nombre from tipotransaccion where codigo = ?";
        return this.jdbcTemplate.query(sql, new MapeoTipoTransaccion(), codigo)
                .stream().findFirst().orElseThrow(() -> new ExcepcionDeProceso(TIPO_TRANSACCION_NO_ENCONTRADA));
    }
}
