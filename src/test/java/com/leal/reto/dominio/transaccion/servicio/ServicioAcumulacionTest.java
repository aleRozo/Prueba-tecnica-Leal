package com.leal.reto.dominio.transaccion.servicio;

import com.leal.reto.dominio.establecimiento.entidad.Establecimiento;
import com.leal.reto.dominio.establecimiento.puerto.RepositorioEstablecimiento;
import com.leal.reto.dominio.establecimiento.testdatabuilder.EstablecimientoTestDataBuilder;
import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.excepcion.ExcepcionValorInvalido;
import com.leal.reto.dominio.tipotransaccion.entidad.TipoTransaccion;
import com.leal.reto.dominio.tipotransaccion.testdatabuilder.TipoTransaccionTestDataBuilder;
import com.leal.reto.dominio.transaccion.entidad.Transaccion;
import com.leal.reto.dominio.transaccion.puerto.RepositorioTransaccion;
import com.leal.reto.dominio.transaccion.testdatabuilder.TransaccionTestDataBuilder;
import com.leal.reto.dominio.usuario.entidad.Usuario;
import com.leal.reto.dominio.usuario.puerto.RepositorioUsuario;
import com.leal.reto.dominio.usuario.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ServicioAcumulacionTest {
    private static final String CODIGO_ACUMULACION = "ACUMULACION";
    private static final String EL_ESTABLECIMIENTO_NO_CUENTA_CON_PUNTOS_DISPONIBLES_PARA_ACUMULAR = "El establecimiento no cuenta con puntos disponibles para acumular";

    private RepositorioEstablecimiento repositorioEstablecimiento;
    private RepositorioUsuario repositorioUsuario;
    private RepositorioTransaccion repositorioTransaccion;
    private ServicioValidarTransaccion servicioValidarTransaccion;
    private ServicioAcumulacion servicioAcumulacion;

    @BeforeEach
    public void setUp(){
        repositorioEstablecimiento = Mockito.mock(RepositorioEstablecimiento.class);
        repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        repositorioTransaccion = Mockito.mock(RepositorioTransaccion.class);
        servicioValidarTransaccion = Mockito.mock(ServicioValidarTransaccion.class);
        servicioAcumulacion = new ServicioAcumulacion(repositorioEstablecimiento, repositorioUsuario, repositorioTransaccion, servicioValidarTransaccion);
    }

    private void mockearRespuestas(Transaccion transaccion) throws ExcepcionDeProceso {
        Usuario usuario = new UsuarioTestDataBuilder().build();
        Establecimiento establecimiento = new EstablecimientoTestDataBuilder().build();
        TipoTransaccion tipoTransaccion = new TipoTransaccionTestDataBuilder().conCodigo(CODIGO_ACUMULACION).build();
        Mockito.when(servicioValidarTransaccion.consultarUsuario(transaccion)).thenReturn(usuario);
        Mockito.when(servicioValidarTransaccion.consultarEstablecimiento(transaccion)).thenReturn(establecimiento);
        Mockito.when(servicioValidarTransaccion.consultarTipoTransaccion(CODIGO_ACUMULACION)).thenReturn(tipoTransaccion.getId());
    }

    @Test
    public void ejecutarTest() throws ExcepcionValorInvalido, ExcepcionDeProceso {
        Transaccion transaccion = new TransaccionTestDataBuilder().buildAcumulacion();
        mockearRespuestas(transaccion);

        servicioAcumulacion.ejecutar(transaccion);
        Mockito.verify(repositorioEstablecimiento, Mockito.times(1)).actualizarPuntosDisponibles(Mockito.any(Establecimiento.class));
        Mockito.verify(repositorioUsuario, Mockito.times(1)).actualizarPuntos(Mockito.any(Usuario.class));
        Mockito.verify(repositorioTransaccion, Mockito.times(1)).crearTransaccion(Mockito.any(Transaccion.class));

    }

    @Test
    public void ejecutarPuntosExcedenLimiteTest() throws ExcepcionValorInvalido, ExcepcionDeProceso {
        Transaccion transaccion = new TransaccionTestDataBuilder().conValor(200000f).buildAcumulacion();
        mockearRespuestas(transaccion);

        try {
            servicioAcumulacion.ejecutar(transaccion);
        } catch (ExcepcionDeProceso e) {
            Assertions.assertEquals(EL_ESTABLECIMIENTO_NO_CUENTA_CON_PUNTOS_DISPONIBLES_PARA_ACUMULAR, e.getMessage());
        }

    }
}
