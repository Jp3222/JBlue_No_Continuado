/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OPagosConsumidor extends Objeto {

    private String consumidor, mes_pagado, personal, dia, mes, anio;

    public OPagosConsumidor(String[] info) {
        super(info);
        this.consumidor = info[1];
        this.mes_pagado = info[2];
        this.personal = info[3];
        this.dia = info[4];
        this.mes = info[5];
        this.anio = info[6];
    }

    public OPagosConsumidor() {
        super();
    }

    public String getConsumidor() {
        return consumidor;
    }

    public String getMes_pagado() {
        return mes_pagado;
    }

    public String getPersonal() {
        return personal;
    }

    public String getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    }

    public String getAnio() {
        return anio;
    }

    @Override
    public void setInfo(String[] info) {
        super.setInfo(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        this.consumidor = info[1];
        this.mes_pagado = info[2];
        this.personal = info[3];
        this.dia = info[4];
        this.mes = info[5];
        this.anio = info[6];
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
