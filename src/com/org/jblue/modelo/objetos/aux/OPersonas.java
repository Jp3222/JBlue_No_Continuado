/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.objetos.aux;

import com.org.jblue.modelo.objetos.Objeto;

/**
 *
 * @author jp
 */
public abstract class OPersonas extends Objeto {

    protected String nombre;
    protected String ap;
    protected String am;

    public OPersonas(String[] info) {
        super(info);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

}
