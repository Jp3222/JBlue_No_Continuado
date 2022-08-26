/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.vistas.class_aux;

/**
 * Interfaz que contiene una funcion comparador la cual sirve para comparar dos
 * objetos de distintas clases
 *
 * @author jp - Juan Pablo viernes 19 agosto
 * @param <T> T objeto generico con el cual se puede especificar el primer
 * objeto
 * @param <K> K objeto generico con el cual se puede especificar el segundo
 * objeto
 */
@FunctionalInterface
public interface Comparador<T, K> {

    /**
     * Metodo que compara dos objetos distintos bajo la condicion que considere
     * el usuario
     *
     * @param x variable que representa el objeto 1 definido por el objeto
     * generico T
     * @param y variable que representa el objeto 2 definido por el objeto
     * generico K
     * @return 0 si la condicion cumple con lo establecido
     * <br>1 si la condicion tiene un resultado mayor
     * <br>-1 si la condicion tiene un resultado menor
     */
    public int comparador(T x, K y);

}
