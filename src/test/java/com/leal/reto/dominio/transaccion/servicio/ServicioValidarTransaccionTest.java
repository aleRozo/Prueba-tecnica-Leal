package com.leal.reto.dominio.transaccion.servicio;

import com.leal.reto.dominio.establecimiento.entidad.Establecimiento;
import com.leal.reto.dominio.establecimiento.puerto.DaoEstablecimiento;
import com.leal.reto.dominio.establecimiento.testdatabuilder.EstablecimientoTestDataBuilder;
import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.excepcion.ExcepcionValorInvalido;
import com.leal.reto.dominio.tipotransaccion.entidad.TipoTransaccion;
import com.leal.reto.dominio.tipotransaccion.puerto.DaoTipoTransaccion;
import com.leal.reto.dominio.tipotransaccion.testdatabuilder.TipoTransaccionTestDataBuilder;
import com.leal.reto.dominio.transaccion.entidad.Transaccion;
import com.leal.reto.dominio.transaccion.testdatabuilder.TransaccionTestDataBuilder;
import com.leal.reto.dominio.usuario.entidad.Usuario;
import com.leal.reto.dominio.usuario.puerto.DaoUsuario;
import com.leal.reto.dominio.usuario.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioValidarTransaccionTest {

    private static final String USUARIO_NO_ENCONTRADO = "El usuario que intenta realizar la acumulación no existe";
    private static final String TIPO_TRANSACCION_NO_ENCONTRADO = "El tipo de transaccion acumulación no existe";
    private static final String ESTABLECIMIENTO_NO_ENCONTRADO = "El establecimiento en el que intenta realizar la acumulación no existe";

    private DaoUsuario daoUsuario;
    private DaoEstablecimiento daoEstablecimiento;
    private DaoTipoTransaccion daoTipoTransaccion;
    private ServicioValidarTransaccion servicioValidarTransaccion;

    @BeforeEach
    public void setUp() {
        daoUsuario = Mockito.mock(DaoUsuario.class);
        daoEstablecimiento = Mockito.mock(DaoEstablecimiento.class);
        daoTipoTransaccion = Mockito.mock(DaoTipoTransaccion.class);

        servicioValidarTransaccion = new ServicioValidarTransaccion(daoUsuario, daoEstablecimiento, daoTipoTransaccion);
    }

    @Test
    public void consultarUsuarioExcepcionTest() throws ExcepcionDeProceso, ExcepcionValorInvalido {
        Transaccion transaccion = new TransaccionTestDataBuilder().buildAcumulacion();
        Mockito.when(daoUsuario.consultarPuntosPorId(transaccion.getIdUsuario())).thenThrow(new ExcepcionDeProceso(USUARIO_NO_ENCONTRADO));
        try {
            servicioValidarTransaccion.consultarUsuario(transaccion);
        } catch (ExcepcionDeProceso e) {
            Assertions.assertEquals(USUARIO_NO_ENCONTRADO, e.getMessage());
            Mockito.verify(daoUsuario, Mockito.times(1)).consultarPuntosPorId(transaccion.getIdUsuario());
        }
    }

    @Test
    public void consultarUsuarioTest() throws ExcepcionDeProceso, ExcepcionValorInvalido {
        Transaccion transaccion = new TransaccionTestDataBuilder().buildAcumulacion();
        Usuario usuario = new UsuarioTestDataBuilder().build();
        Mockito.when(daoUsuario.consultarPuntosPorId(transaccion.getIdUsuario())).thenReturn(usuario);
        servicioValidarTransaccion.consultarUsuario(transaccion);
        Mockito.verify(daoUsuario, Mockito.times(1)).consultarPuntosPorId(transaccion.getIdUsuario());
    }

    @Test
    public void consultarTipoTransaccionExcepcionTest() throws ExcepcionDeProceso, ExcepcionValorInvalido {
        Mockito.when(daoTipoTransaccion.consultarPorCodigo("acumulacion")).thenThrow(new ExcepcionDeProceso(TIPO_TRANSACCION_NO_ENCONTRADO));
        try {
            servicioValidarTransaccion.consultarTipoTransaccion("acumulacion");
        } catch (ExcepcionDeProceso e) {
            Assertions.assertEquals(TIPO_TRANSACCION_NO_ENCONTRADO, e.getMessage());
            Mockito.verify(daoTipoTransaccion, Mockito.times(1)).consultarPorCodigo("acumulacion");
        }
    }

    @Test
    public void consultarTipoTransaccionTest() throws ExcepcionDeProceso, ExcepcionValorInvalido {
        TipoTransaccion tipoTransaccion = new TipoTransaccionTestDataBuilder().build();
        Mockito.when(daoTipoTransaccion.consultarPorCodigo("acumulacion")).thenReturn(tipoTransaccion);
        servicioValidarTransaccion.consultarTipoTransaccion("acumulacion");
        Mockito.verify(daoTipoTransaccion, Mockito.times(1)).consultarPorCodigo("acumulacion");
    }

    @Test
    public void consultarEstablecimientoExcepcionTest() throws ExcepcionDeProceso, ExcepcionValorInvalido {
        Transaccion transaccion = new TransaccionTestDataBuilder().buildAcumulacion();
        Mockito.when(daoEstablecimiento.consultarPorId(transaccion.getIdEstablecimiento())).thenThrow(new ExcepcionDeProceso(ESTABLECIMIENTO_NO_ENCONTRADO));
        try {
            servicioValidarTransaccion.consultarEstablecimiento(transaccion);
        } catch (ExcepcionDeProceso e) {
            Assertions.assertEquals(ESTABLECIMIENTO_NO_ENCONTRADO, e.getMessage());
            Mockito.verify(daoEstablecimiento, Mockito.times(1)).consultarPorId(transaccion.getIdEstablecimiento());
        }
    }

    @Test
    public void consultarEstablecimientoTest() throws ExcepcionDeProceso, ExcepcionValorInvalido {
        Transaccion transaccion = new TransaccionTestDataBuilder().buildAcumulacion();
        Establecimiento establecimiento = new EstablecimientoTestDataBuilder().build();
        Mockito.when(daoEstablecimiento.consultarPorId(transaccion.getIdEstablecimiento())).thenReturn(establecimiento);
        servicioValidarTransaccion.consultarEstablecimiento(transaccion);
        Mockito.verify(daoEstablecimiento, Mockito.times(1)).consultarPorId(transaccion.getIdEstablecimiento());
    }
}
