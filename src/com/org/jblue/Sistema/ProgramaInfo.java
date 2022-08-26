/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author jp
 */
public class ProgramaInfo {

    public static final long VERSION_UID = 1L;
    public static final Icon ICON = new ImageIcon(ProgramaInfo.class.getResource("/com/org/jblue/Icons"));
    public static final String NOMBRE = "jblue";
    public static final String VERSION = "0.0";
    public static final String[] SECCIONES = {
        "Inicio de Sesion", "Menu Principal", "Base de datos"
    };

    /**
     * Metodo que devuelve un titulo segun el indice especificado
     * <br> 0 Inicio de sesion
     * <br> 1 Menu Prinicipal
     *
     * @param i indice requerido
     * @return el titulo requerido
     */
    public static String getSECCIONES(int i) {
        return SECCIONES[i];
    }

}
