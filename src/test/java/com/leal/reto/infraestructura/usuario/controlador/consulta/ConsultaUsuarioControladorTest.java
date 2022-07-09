package com.leal.reto.infraestructura.usuario.controlador.consulta;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leal.reto.ApplicationMock;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ConsultaUsuarioControlador.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultaUsuarioControladorTest {

    private static final String IDENTIFICACION = "1";
    private static final String IDENTIFICACION_NO_EXISTE = "2";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void consultarPuntosPorIdentificacionTest() throws Exception {

        MockHttpServletRequestBuilder request = get("/usuario/" + IDENTIFICACION)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mocMvc.perform(request).andExpect(status().isOk()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(200);

        JsonNode jsonResponse = objectMapper.readTree(result.getResponse().getContentAsByteArray());

        assertThat(jsonResponse.isObject()).isTrue();
        assertThat(jsonResponse.has("id")).isTrue();
        assertThat(jsonResponse.has("identificacion")).isTrue();
        assertThat(jsonResponse.has("nombre")).isTrue();
        assertThat(jsonResponse.has("apellido")).isTrue();
        assertThat(jsonResponse.has("puntos")).isTrue();
    }

    @Test
    public void consultarPuntosPorIdentificacionUsuarioNoExisteTest() throws Exception {

        MockHttpServletRequestBuilder request = get("/usuario/" + IDENTIFICACION_NO_EXISTE)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mocMvc.perform(request).andExpect(status().isNotAcceptable()).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(406);

        JsonNode jsonResponse = objectMapper.readTree(result.getResponse().getContentAsByteArray());

        assertThat(jsonResponse.isObject()).isTrue();
        assertThat(jsonResponse.has("mensaje")).isTrue();
        assertThat(jsonResponse.has("status")).isTrue();
    }
}
