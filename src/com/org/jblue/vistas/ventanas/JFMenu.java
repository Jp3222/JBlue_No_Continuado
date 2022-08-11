/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.org.jblue.vistas.ventanas;

import com.org.jblue.controlador.ControladorVentanas;
import com.org.jblue.vistas.class_aux.ClassVentana;
import com.org.jblue.vistas.class_aux.Funciones;
import com.org.jblue.vistas.vistas.JPBaseDatos;
import com.org.jblue.vistas.vistas.JPCaja;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author jp
 */
public class JFMenu extends ClassVentana implements Funciones {

    private final JFLogin login;
    private JPCaja caja;
    private JPBaseDatos administracion;
    private final Toolkit toolkit;
    private ControladorVentanas ventanas;

    /**
     * Creates new form Menu
     *
     * @param login
     */
    public JFMenu(JFLogin login) {
        this.login = login;
        this.toolkit = Toolkit.getDefaultToolkit();
        initComponents();
        call();

    }

    @Override
    public final void call() {
        info();
        init();
        addListeners();
    }

    @Override
    public void info() {
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setMaximumSize(toolkit.getScreenSize());
    }

    @Override
    public void init() {
        //vistas graficas
        this.caja = new JPCaja(this);
        this.administracion = new JPBaseDatos(this);
        this.administracion.setVisible(false);
        this.mostrarCaja();
        ventanas = new ControladorVentanas();
        this.addWindowListener(ventanas);
    }

    @Override
    public void addListeners() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1200, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1200, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void mostrarAdministracion() {
        this.caja.setVisible(false);
        this.administracion.setVisible(true);
        this.setContentPane(administracion);
    }

    public void mostrarCaja() {
        this.caja.setVisible(true);
        this.administracion.setVisible(false);
        this.setContentPane(caja);
    }

    public void irLogin() {
        this.dispose();
        ventanas.finSesion();
        sleep();
        login.setVisible(true);
    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(JFMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
