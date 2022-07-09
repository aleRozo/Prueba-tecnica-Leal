package com.leal.reto.dominio.usuario.testdatabuilder;

import com.leal.reto.dominio.usuario.entidad.Usuario;

public class UsuarioTestDataBuilder {
    private int id;
    private String identificacion;
    private String nombre;
    private String apellido;
    private int puntos;

    public UsuarioTestDataBuilder() {
        this.id = 1;
        this.identificacion = "1";
        this.nombre = "Alejandra";
        this.apellido = "Rozo";
        this.puntos = 10;
    }

    public Usuario build() {
        return new Usuario(id, identificacion, nombre, apellido, puntos);
    }

}
