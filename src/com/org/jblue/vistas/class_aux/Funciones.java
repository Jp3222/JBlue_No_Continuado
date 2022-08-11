/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.org.jblue.vistas.class_aux;

/**
 *
 * @author jp
 */
public interface Funciones {

    /**
     * Metodo especifico para ser definido como final.
     * <br> funcionalidad: llamar a los metodos
     * <br> 1 info
     * <br> 2 init
     * <br> 3 addListeners
     * <br>en ese especifico orden
     */
    public void call();

    /**
     * Metodo especifico para definir todos los atributos de la ventana actual
     * ejem: titulo, nombre del programa, version, etc.
     */
    public void info();

    /**
     * metodo especifico para inicializar variables e darles sus respectivos
     * atributos
     */
    public void init();

    /**
     * Metodo especifico para añadir listeners o eventos de los distintos
     * componentes graficos
     */
    public void addListeners();

}
