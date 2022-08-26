/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.controlador;

import com.org.jblue.Sistema.Sesiones;
import com.org.jblue.Sistema.funciones.EncriptadorAES;
import com.org.jblue.modelo.objetos.OPersonal;
import com.org.jblue.modelo.operaciones.OperacionesPersonal;
import com.org.jblue.vistas.ventanas.JFLogin;
import com.org.jblue.vistas.ventanas.JFMenu;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author jp
 */
public class CLogin extends Controlador {

    private final JFLogin login;
    private final OperacionesPersonal operaciones;
    private final CLogin_Adaptador adaptador;
    private final Sesiones sesiones;
    private JFMenu menu;
    private final EncriptadorAES encriptador;

    public CLogin(JFLogin login) {
        this.login = login;
        this.operaciones = new OperacionesPersonal();
        this.adaptador = new CLogin_Adaptador();
        this.sesiones = Sesiones.getInstancia();
        encriptador = EncriptadorAES.getInstancia();
    }

    public void setMenu(JFMenu menu) {
        this.menu = menu;
    }

    public CLogin_Adaptador getAdaptador() {
        return adaptador;
    }

    public class CLogin_Adaptador extends Adaptador {

        @Override
        public void actionPerformed(ActionEvent e) {
            login();
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                login();
            }
        }

        private void login() {
            if (!login.camposVacios()) {
                final String usuario = login.getUsuario();
                final String contra = login.getContraseña();
                //
                final String valor = encriptador.encriptar(usuario, contra);
                final String clave = encriptador.encriptar(contra, usuario);
                OPersonal get = operaciones.get("usuario = '" + valor + "' and contra = '" + clave + "'");
                if (get != null && get.isExiste()) {
                    login.dispose();
                    login.reinicio();
                    sleep();
                    menu.setVisible(true);
                    sesiones.setUsuario(get);
                    sesiones.registroInicioDeSesiones();
                } else {
                    JOptionPane.showMessageDialog(null, "El usuario y/o la contraseña son incorrectos", "Error de sesion", JOptionPane.WARNING_MESSAGE);
                }
            }
        }

        public void sleep() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
