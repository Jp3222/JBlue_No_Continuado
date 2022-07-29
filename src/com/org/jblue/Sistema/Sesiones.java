/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema;

import com.jsql.conexion.Conexion;
import com.org.jblue.modelo.objetos.OPersonal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author jp
 */
public class Sesiones {

    private static final Sesiones instancia = new Sesiones();

    public static Sesiones getInstancia() {
        return instancia;
    }

    private OPersonal usuario;
    private final Conexion cn;

    private Sesiones() {
        this.cn = Conexion.getInstancia();

    }

    public OPersonal getUsuario() {
        return usuario;
    }

    public void setUsuario(OPersonal usuario) {
        this.usuario = usuario;
    }

    public void InicioDeSesiones() {
        LocalTime hora = LocalTime.now();
        LocalDate fecha = LocalDate.now();
        cn.insert(
                "sesiones",
                cn.getCampos("usuario", "fecha", "hora"),
                cn.getDatos(
                        usuario.getId(),
                        fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                        hora.format(DateTimeFormatter.ofPattern("ss-mm-HH"))
                )
        );

    }

    public void FinDeSesiones() {
        LocalTime hora = LocalTime.now();
        LocalDate fecha = LocalDate.now();
        cn.insert(
                "sesiones",
                cn.getCampos("usuario", "fecha", "hora"),
                cn.getDatos(
                        usuario.getId(),
                        fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                        hora.format(DateTimeFormatter.ofPattern("ss-mm-HH"))
                )
        );
        this.usuario = null;
    }
}
