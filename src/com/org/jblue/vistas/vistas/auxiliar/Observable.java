/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.vistas.vistas.auxiliar;

import java.util.ArrayList;

/**
 *
 * @author jp
 */
public interface Observable {

    public final ArrayList<Observador> observadores = new ArrayList<>();

    public default void notificar() {
        observadores.forEach(o -> o.actualizar());
    }

    public default boolean add(Observador o) {
        return observadores.add(o);
    }

    public default Observador remove(int i) {
        return observadores.remove(i);
    }

}
