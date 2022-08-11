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
 * Clase Sesiones
 * <br> Se encarga de registrar los inicios y fines se sesion del programa y
 * contener al usuario que esta haciendo uso del programa en ese momento
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

    public boolean isAbierto() {
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        boolean dia_correcto = fecha.getDayOfWeek().getValue() == 5 || fecha.getDayOfWeek().getValue() == 6;
        boolean hora_correcta = (hora.getHour() > 6 && hora.getMinute() > 59 && hora.getSecond() > 59) && (hora.getHour() > 9 && hora.getMinute() < 0 && hora.getSecond() > 0);
        return true;
    }

    public void InicioDeSesiones() {
        LocalTime hora = LocalTime.now();
        LocalDate fecha = LocalDate.now();
        cn.insert(
                "historial_movimientos",
                cn.getCampos("movimiento",
                        "personal",
                        "fecha",
                        "hora"
                ),
                cn.getDatos("101",
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
                "historial_movimientos",
                cn.getCampos("movimiento",
                        "personal",
                        "fecha",
                        "hora"
                ),
                cn.getDatos("102",
                        usuario.getId(),
                        fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                        hora.format(DateTimeFormatter.ofPattern("ss-mm-HH"))
                )
        );
        this.usuario = null;
    }
}
