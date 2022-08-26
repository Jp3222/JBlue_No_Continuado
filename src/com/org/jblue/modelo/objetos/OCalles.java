/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OCalles extends Objeto { 
    
    private String nombre;
    private String numero;

    public OCalles(String[] info) {
        super(info);
        this.nombre = info[1];
        this.numero = info[2];
    }

    public OCalles() {
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public void setInfo(String[] info) {
        super.setInfo(info);
        this.nombre = info[1];
        this.numero = info[2];
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
