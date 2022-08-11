/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OConsumidores extends Objeto {

    private String nombre,
            ap,
            am,
            titular,
            registro,
            estado;

    public OConsumidores(String[] info) {
        super(info);
        this.nombre = info[1];
        this.ap = info[2];
        this.am = info[3];
        this.titular = info[4];
        this.registro = info[5];
        this.estado = info[6];
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

    public String getTitular() {
        return titular;
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
        this.titular = info[4];
        this.registro = info[5];
        this.estado = info[6];
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
