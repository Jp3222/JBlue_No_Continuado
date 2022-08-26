/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OTitulares extends Objeto {

    private String nombre,
            ap,
            am,
            calle,
            toma,
            consumidores,
            registro,
            estado;

    public OTitulares(String[] info) {
        super(info);
        this.nombre = info[1];
        this.ap = info[2];
        this.am = info[3];
        this.calle = info[4];
        this.toma = info[5];
        this.consumidores = info[6];
        this.registro = info[7];
        this.estado = info[8];
    }

    public OTitulares() {
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public String getAp() {
        return ap;
    }

    public String getAm() {
        return am;
    }

    public String getCalle() {
        return calle;
    }

    public String getToma() {
        return toma;
    }

    public String getConsumidores() {
        return consumidores;
    }

    public String getRegistro() {
        return registro;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public void setInfo(String[] info) {
        super.setInfo(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        this.nombre = info[1];
        this.ap = info[2];
        this.am = info[3];
        this.calle = info[4];
        this.toma = info[5];
        this.consumidores = info[6];
        this.registro = info[7];
        this.estado = info[8];
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
