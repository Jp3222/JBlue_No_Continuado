/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import com.org.jblue.Sistema.funciones.EncriptadorAES;

/**
 *
 * @author jp
 */
public class Class {

    public void main(String[] args) {
        String claveEncriptacion = "0000";
        String datosOriginales = "root";

        EncriptadorAES encriptador = EncriptadorAES.getInstancia();

        String t = encriptador.encriptar(datosOriginales, claveEncriptacion);
        String k = encriptador.encriptar(claveEncriptacion, datosOriginales);

        System.out.println("valor: " + t);
        System.out.println("llave: " + k);

        String valor = "DYjV5v/rZTjlHOhU5GTewg==";
        String llave = "Gn6/q0zfTijiIiUDD5bnvA==";

        //String t2 = encriptador.desencriptar(t, claveEncriptacion);
        //String k2 = encriptador.desencriptar(k, datosOriginales);

        //System.out.println("valor: " + t2);
        //System.out.println("llave: " + k2);
        
        System.out.println(encriptador.comparadorEncriptado(valor, llave, datosOriginales, claveEncriptacion));
        
    }

}
