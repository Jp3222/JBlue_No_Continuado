/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue;

import com.org.jblue.vistas.class_aux.Comparador;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class Func {

    public static String formatoDeSalida(String txt) {
        return txt.replace("_", " ");
    }

    public static String formatoDeEntrada(String txt) {
        return txt.toUpperCase().replace(" ", "_").replace(".|,", "");
    }

    public static String[] getArrayFormatoDeEntrada(String array[]) {
        for (int i = 0; i < array.length; i++) {
            array[i] = formatoDeEntrada(array[i]);
        }
        return array;
    }

    public static String[] getArrayFormatoDeSalida(String array[]) {
        for (int i = 0; i < array.length; i++) {
            array[i] = formatoDeSalida(array[i]);
        }
        return array;
    }

    /**
     * Metodo que compara
     *
     * @param <T>
     * @param <K>
     * @param lista
     * @param buscado
     * @param fun
     * @return
     */
    public static <T, K> T getBuscarBinario(ArrayList<T> lista, K buscado, Comparador<T, K> fun) {
        int izq = 0;
        int der = lista.size() - 1;
        int centro;
        while (izq <= der) {
            centro = (izq + der) / 2;
            T obj = lista.get(centro);
            int mov = fun.comparador(obj, buscado);
            switch (mov) {
                case 0:
                    return obj;
                case -1:
                    der = centro - 1;
                    break;
                case 1:
                    izq = centro + 1;
                    break;
                default:
                    break;
            }
        }
        return null;
    }

    public static <T, K> T getBuscarLineal(ArrayList<T> lista, K buscado, Comparador<T, K> fun) {
        for (T t : lista) {
            if (fun.comparador(t, buscado) == 0) {
                return t;
            }
        }
        return null;
    }

}
