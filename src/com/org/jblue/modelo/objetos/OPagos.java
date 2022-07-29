/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OPagos extends Objeto {

    private String usuario;
    private String mesPagado;
    private String dia;
    private String mes;
    private String anio;

    public OPagos(String info[]) {
        super(info);
        usuario = info[1];
        mesPagado = info[2];
        dia = info[3];
        mes = info[4];
        anio = info[5];

    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getMesPagado() {
        return mesPagado;
    }

    public void setMesPagado(String mesPagado) {
        this.mesPagado = mesPagado;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

}
