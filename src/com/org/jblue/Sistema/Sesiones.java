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

    /**
     * *
     * Instancia unica de esta clase
     */
    private static final Sesiones instancia = new Sesiones();

    /**
     * Metodo que retorna una instancia unica de la clase "Sesiones"
     *
     * @return un objeto de tipo "sesiones"
     */
    public synchronized static Sesiones getInstancia() {
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

    /**
     * Asiga al usuario que esta en sesion
     *
     * @param usuario - usuario que ha iniciado sesion en el programa
     */
    public void setUsuario(OPersonal usuario) {
        this.usuario = usuario;
    }

    /**
     * Este metodo evalua si el dia y la hora son los corrector para abrir el
     * programa
     *
     * @return true si es el dia 5 o 6 de la semana y si la hora esta entre las
     * 7 y 9
     */
    public boolean isAbierto() {
        LocalDate fecha = LocalDate.now();
        LocalTime hora = LocalTime.now();
        boolean dia_correcto = fecha.getDayOfWeek().getValue() == 5 || fecha.getDayOfWeek().getValue() == 6;
        boolean hora_correcta = (hora.getHour() > 6 && hora.getMinute() > 59 && hora.getSecond() > 59) && (hora.getHour() > 9 && hora.getMinute() < 0 && hora.getSecond() > 0);
        return true;
    }

    /**
     * Metodo que registra un inicio de sesion en la base de datos
     * <br>datos a guardar:
     * <br> movimiento( codigo: 101)
     * <br> personal(id)
     * <br> fecha(dd-MM-yyyy)
     * <br> hora(ss-mm-HH)
     */
    public void registroInicioDeSesiones() {
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

    /**
     * Metodo que registra un Fin de sesion en la base de datos
     * <br>datos a guardar:
     * <br> movimiento( codigo: 102)
     * <br> personal(id)
     * <br> fecha(dd-MM-yyyy)
     * <br> hora(ss-mm-HH)
     */
    public void registroFinDeSesiones() {
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
