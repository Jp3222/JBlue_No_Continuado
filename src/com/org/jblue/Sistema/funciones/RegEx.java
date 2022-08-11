/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.funciones;

/**
 *
 * @author jp
 */
public class RegEx {

    public static final String PALABRAS_NO_VALIDAS = "(NOT|AND|OR)*";
    public static final String SIMBOLOS_NO_VALIDAS = "(-|\\+|/|\\*)*";
    public static final String NUMEROS = "[0-9]*";
    public static final String TEXTO = "[a-zA-Z]*";
    
    public static boolean Contiene_PALABRAS_NO_VALIDAS(String text) {
        return text.matches(PALABRAS_NO_VALIDAS);
    }

    public static boolean Contiene_SIMBOLOS_NO_VALIDAS(String text) {
        return text.matches(text);
    }

    private RegEx() {
    }
}
