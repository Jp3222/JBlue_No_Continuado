/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue;

/**
 *
 * @author jp
 */
public class Const {

    //Meses del año
    /*
    Constante que guarda los meses del año
    se usan como registro en la base de datos
     */
    public static final String MESES[] = {
        "ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC"
    };

    /**
     * Retorna el mes indicado por el parametro i
     *
     * @param i mes del año empezando por 0
     * @return Mes seleccionado
     */
    public static String getMes(int i) {
        return MESES[i - 1];
    }
    //Campos de la base de datos
    public static final String[] BD_PERSONAL = {
        "id", "nombre", "apellidos", "cargo", "usuario", "contra"
    };
    public static final String[] BD_USUARIOS = {
        "id", "nombre", "ap", "am", "calle", "toma", "fecha_registro", "estado"
    };
    public static final String[] BD_TOMAS = {
        "id", "tipo", "precio"
    };
    public static final String[] BD_CALLES = {
        "id", "nombre", "numero"
    };

    private Const() {

    }
}
