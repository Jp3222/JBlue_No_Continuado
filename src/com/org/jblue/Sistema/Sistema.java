/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema;

import com.jsql.conexion.Conexion;
import com.org.JFiles.Archivos.AText;
import com.org.jblue.Sistema.cache.cacheBD.Cache;
import com.org.jblue.vistas.ventanas.JFConfgBD;
import com.org.jblue.vistas.ventanas.JFLogin;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author jp
 */
public class Sistema {

    private final static Sistema instancia = new Sistema();

    public synchronized static Sistema getInstancia() {
        return instancia;
    }

    private final ConstructorDeArchivos contructor;
    private Conexion cn;
    private Cache memoriaCache;

    public Sistema() {
        this.contructor = ConstructorDeArchivos.getInstancia();
        init();
    }

    private void init() {
        if (!contructor.getFiles(1).exists()) {
            construir();
            return;
        }
        AText o = new AText();
        String Leer_Archivo = o.Leer_Archivo(contructor.getFiles(1));
        String[] usuarioBD = Leer_Archivo.split(",");
        this.cn = Conexion.getInstancia(usuarioBD[0], usuarioBD[1], usuarioBD[2]);
        this.memoriaCache = Cache.getInstancia();
        this.memoriaCache.init();
        if (!cn.isConexion()) {
            JOptionPane.showMessageDialog(null, "Conexion Fallida", "Estado de Conexion", JOptionPane.ERROR_MESSAGE);
            System.out.println("conexion fallida");
        } else {
            System.out.println("conexion exitosa");
        }
    }

    public void construir() {
        JFConfgBD o = new JFConfgBD();
        o.setVisible(true);
    }

    public boolean run() {
        try {
            JFLogin login = new JFLogin();
            Runnable runnable = () -> login.setVisible(true);
            SwingUtilities.invokeLater(runnable);
            return true;
        } catch (Exception e) {
            System.out.println(e.getCause().getMessage());
            return false;
        }
    }
}
