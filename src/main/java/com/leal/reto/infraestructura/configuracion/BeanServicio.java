package com.leal.reto.infraestructura.configuracion;

import com.leal.reto.dominio.establecimiento.puerto.DaoEstablecimiento;
import com.leal.reto.dominio.establecimiento.puerto.RepositorioEstablecimiento;
import com.leal.reto.dominio.parametros.puerto.DaoParametro;
import com.leal.reto.dominio.tipotransaccion.puerto.DaoTipoTransaccion;
import com.leal.reto.dominio.transaccion.puerto.RepositorioTransaccion;
import com.leal.reto.dominio.transaccion.servicio.ServicioAcumulacion;
import com.leal.reto.dominio.transaccion.servicio.ServicioRedencion;
import com.leal.reto.dominio.transaccion.servicio.ServicioValidarTransaccion;
import com.leal.reto.dominio.usuario.puerto.DaoUsuario;
import com.leal.reto.dominio.usuario.puerto.RepositorioUsuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioAcumulacion servicioAcumulacion(
            RepositorioEstablecimiento repositorioEstablecimiento,
            RepositorioUsuario repositorioUsuario,
            RepositorioTransaccion repositorioTransaccion,
            ServicioValidarTransaccion servicioValidarTransaccion) {
        return new ServicioAcumulacion(repositorioEstablecimiento, repositorioUsuario,
                repositorioTransaccion, servicioValidarTransaccion);
    }

    @Bean
    public ServicioRedencion servicioRedencion(
            DaoParametro daoParametro,
            RepositorioUsuario repositorioUsuario,
            RepositorioTransaccion repositorioTransaccion,
            ServicioValidarTransaccion servicioValidarTransaccion) {
       return new ServicioRedencion(daoParametro, repositorioUsuario, repositorioTransaccion, servicioValidarTransaccion);
    }

    @Bean
    public ServicioValidarTransaccion servicioValidarTransaccion(
            DaoUsuario daoUsuario,
            DaoEstablecimiento daoEstablecimiento,
            DaoTipoTransaccion daoTipoTransaccion) {
        return new ServicioValidarTransaccion(daoUsuario, daoEstablecimiento, daoTipoTransaccion);
    }
}
