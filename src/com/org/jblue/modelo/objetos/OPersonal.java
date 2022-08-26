/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.objetos;

/**
 *
 * @author jp
 */
public class OPersonal extends Objeto {

    private String nombre;
    private String apellidos;
    private String cargo;
    private String usuario;
    private String contraseña;

    public OPersonal(String[] info) {
        super(info);
        this.nombre = info[1];
        this.apellidos = info[2];
        this.cargo = info[3];
        this.usuario = info[4];
        this.contraseña = info[5];
    }

    public OPersonal() {
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCargo() {
        return cargo;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    @Override
    public void setInfo(String[] info) {
        super.setInfo(info); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        this.nombre = info[1];
        this.apellidos = info[2];
        this.cargo = info[3];
        this.usuario = info[4];
        this.contraseña = info[5];
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }

}
