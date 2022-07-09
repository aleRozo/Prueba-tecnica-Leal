package com.leal.reto.infraestructura.transaccion.controlador.comando;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leal.reto.ApplicationMock;
import com.leal.reto.aplicacion.transaccion.comando.ComandoTransaccion;
import com.leal.reto.infraestructura.transaccion.controlador.comando.testdatabuilder.ComandoTransaccionTestDataBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoTransaccionControlador.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ComandoTransaccionControladorTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;


    @Test
    public void realizarAcumulacionTest() throws Exception {
        ComandoTransaccion comandoTransaccion = new ComandoTransaccionTestDataBuilder().build();
        MockHttpServletRequestBuilder request = post("/transaccion/acumulacion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoTransaccion));

        MvcResult result = mocMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

        JsonNode jsonResponse = objectMapper.readTree(result.getResponse().getContentAsByteArray());

        assertThat(jsonResponse.isObject()).isTrue();
        assertThat(jsonResponse.has("datos")).isTrue();
        assertThat(jsonResponse.has("status")).isTrue();
    }

    @Test
    public void realizarAcumulacionCompraCeroTest() throws Exception {
        ComandoTransaccion comandoTransaccion = new ComandoTransaccionTestDataBuilder().conValorCompra(0f).build();
        MockHttpServletRequestBuilder request = post("/transaccion/acumulacion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoTransaccion));

        MvcResult result = mocMvc.perform(request).andExpect(status().isBadRequest()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(400);

        JsonNode jsonResponse = objectMapper.readTree(result.getResponse().getContentAsByteArray());

        assertThat(jsonResponse.isObject()).isTrue();
        assertThat(jsonResponse.has("mensaje")).isTrue();
        assertThat(jsonResponse.has("status")).isTrue();
    }

    @Test
    public void realizarAcumulacionEstablecimientoNoEncontradoTest() throws Exception {
        ComandoTransaccion comandoTransaccion = new ComandoTransaccionTestDataBuilder().conIdEstablecimiento(1000).build();
        MockHttpServletRequestBuilder request = post("/transaccion/acumulacion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoTransaccion));

        MvcResult result = mocMvc.perform(request).andExpect(status().isNotAcceptable()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(406);

        JsonNode jsonResponse = objectMapper.readTree(result.getResponse().getContentAsByteArray());

        assertThat(jsonResponse.isObject()).isTrue();
        assertThat(jsonResponse.has("mensaje")).isTrue();
        assertThat(jsonResponse.has("status")).isTrue();
    }

    @Test
    public void realizarRedencionTest() throws Exception {
        ComandoTransaccion comandoTransaccion = new ComandoTransaccionTestDataBuilder().build();
        MockHttpServletRequestBuilder request = post("/transaccion/redencion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoTransaccion));

        MvcResult result = mocMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

        JsonNode jsonResponse = objectMapper.readTree(result.getResponse().getContentAsByteArray());

        assertThat(jsonResponse.isObject()).isTrue();
        assertThat(jsonResponse.has("datos")).isTrue();
        assertThat(jsonResponse.has("status")).isTrue();
    }

    @Test
    public void realizarRedencionCeroPuntosARedimirTest() throws Exception {
        ComandoTransaccion comandoTransaccion = new ComandoTransaccionTestDataBuilder().conPuntosRedimir(0).build();
        MockHttpServletRequestBuilder request = post("/transaccion/redencion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoTransaccion));

        MvcResult result = mocMvc.perform(request).andExpect(status().isBadRequest()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(400);

        JsonNode jsonResponse = objectMapper.readTree(result.getResponse().getContentAsByteArray());

        assertThat(jsonResponse.isObject()).isTrue();
        assertThat(jsonResponse.has("mensaje")).isTrue();
        assertThat(jsonResponse.has("status")).isTrue();
    }

    @Test
    public void realizarRedencionUsuarioNoEncontradoTest() throws Exception {
        ComandoTransaccion comandoTransaccion = new ComandoTransaccionTestDataBuilder().conIdUsuario(1000).build();
        MockHttpServletRequestBuilder request = post("/transaccion/redencion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(comandoTransaccion));

        MvcResult result = mocMvc.perform(request).andExpect(status().isNotAcceptable()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(406);

        JsonNode jsonResponse = objectMapper.readTree(result.getResponse().getContentAsByteArray());

        assertThat(jsonResponse.isObject()).isTrue();
        assertThat(jsonResponse.has("mensaje")).isTrue();
        assertThat(jsonResponse.has("status")).isTrue();
    }
}
