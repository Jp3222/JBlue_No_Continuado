/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.org.jblue.modelo.operaciones.aux;

import com.org.jblue.modelo.objetos.Objeto;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public interface Operaciones {

    boolean insertar(String[] valores);

    boolean eliminar(String where);

    boolean actualizar(String campo, String valor, String where);

    boolean actualizar(String campos[], String valores[], String where);

    <T extends Objeto> T get(String where);

    <T extends Objeto> ArrayList<T> getLista(String where);

}
