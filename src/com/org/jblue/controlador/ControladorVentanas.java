/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.controlador;

import com.org.jblue.Sistema.Sesiones;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author jp
 */
public class ControladorVentanas extends WindowAdapter {

    Sesiones sesion = Sesiones.getInstancia();

    @Override
    public void windowClosing(WindowEvent e) {
        sesion.FinDeSesiones();
    }

    public void finSesion() {
        sesion.FinDeSesiones();
    }

}
