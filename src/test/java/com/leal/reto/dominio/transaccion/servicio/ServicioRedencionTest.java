package com.leal.reto.dominio.transaccion.servicio;

import com.leal.reto.dominio.establecimiento.entidad.Establecimiento;
import com.leal.reto.dominio.establecimiento.testdatabuilder.EstablecimientoTestDataBuilder;
import com.leal.reto.dominio.excepcion.ExcepcionDeProceso;
import com.leal.reto.dominio.excepcion.ExcepcionValorInvalido;
import com.leal.reto.dominio.parametro.testdatabuilder.ParametroTestDataBuilder;
import com.leal.reto.dominio.parametros.entidad.Parametro;
import com.leal.reto.dominio.parametros.puerto.DaoParametro;
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

public class ServicioRedencionTest {

    private static final String CODIGO_REDENCION = "REDENCION";
    private static final String CODIGO_PARAMETRO_MINIMO_PARA_REDIMIR = "PUNTOS_MINIMOS_PARA_REDIMIR";
    private static final String EL_USUARIO_NO_TIENE_LA_CANTIDAD_MINIMA_DE_PUNTOS_PARA_PODER_REDIMIR = "El usuario no tiene la cantidad minima de puntos para poder redimir";
    private static final String EL_USUARIO_NO_TIENE_SUFICIENTES_PUNTOS_PARA_REDIMIR = "El usuario no tiene suficientes puntos para redimir";

    private DaoParametro daoParametro;
    private RepositorioUsuario repositorioUsuario;
    private RepositorioTransaccion repositorioTransaccion;
    private ServicioValidarTransaccion servicioValidarTransaccion;
    private ServicioRedencion servicioRedencion;

    @BeforeEach
    public void setUp(){
        daoParametro = Mockito.mock(DaoParametro.class);
        repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        repositorioTransaccion = Mockito.mock(RepositorioTransaccion.class);
        servicioValidarTransaccion = Mockito.mock(ServicioValidarTransaccion.class);
        servicioRedencion = new ServicioRedencion(daoParametro, repositorioUsuario, repositorioTransaccion, servicioValidarTransaccion);
    }

    @Test
    public void ejecutarInvalidoMinimoARedimirTest() throws ExcepcionValorInvalido, ExcepcionDeProceso {
        Transaccion transaccion = new TransaccionTestDataBuilder().buildRedencion();
        mockearRespuestas(transaccion);
        try {
            servicioRedencion.ejecutar(transaccion);
        } catch (ExcepcionDeProceso e) {
            Assertions.assertEquals(EL_USUARIO_NO_TIENE_LA_CANTIDAD_MINIMA_DE_PUNTOS_PARA_PODER_REDIMIR, e.getMessage());
        }
    }

    @Test
    public void ejecutarElUsuarioNoTienePuntosTest() throws ExcepcionValorInvalido, ExcepcionDeProceso {
        Transaccion transaccion = new TransaccionTestDataBuilder().conPuntos(11).buildRedencion();
        mockearRespuestas(transaccion);
        try {
            servicioRedencion.ejecutar(transaccion);
        } catch (ExcepcionDeProceso e) {
            Assertions.assertEquals(EL_USUARIO_NO_TIENE_SUFICIENTES_PUNTOS_PARA_REDIMIR, e.getMessage());
        }
    }

    @Test
    public void ejecutarTest() throws ExcepcionValorInvalido, ExcepcionDeProceso {
        Transaccion transaccion = new TransaccionTestDataBuilder().conPuntos(10).buildRedencion();
        mockearRespuestas(transaccion);
        servicioRedencion.ejecutar(transaccion);
        Mockito.verify(repositorioUsuario, Mockito.times(1)).actualizarPuntos(Mockito.any(Usuario.class));
    }

    private void mockearRespuestas(Transaccion transaccion) throws ExcepcionDeProceso {
        Usuario usuario = new UsuarioTestDataBuilder().build();
        Establecimiento establecimiento = new EstablecimientoTestDataBuilder().build();
        TipoTransaccion tipoTransaccion = new TipoTransaccionTestDataBuilder().conCodigo(CODIGO_REDENCION).build();
        Parametro parametro = new ParametroTestDataBuilder().build();
        Mockito.when(servicioValidarTransaccion.consultarUsuario(transaccion)).thenReturn(usuario);
        Mockito.when(servicioValidarTransaccion.consultarEstablecimiento(transaccion)).thenReturn(establecimiento);
        Mockito.when(servicioValidarTransaccion.consultarTipoTransaccion(CODIGO_REDENCION)).thenReturn(tipoTransaccion.getId());
        Mockito.when(daoParametro.consultarPorCodigo(CODIGO_PARAMETRO_MINIMO_PARA_REDIMIR)).thenReturn(parametro);
    }
}
