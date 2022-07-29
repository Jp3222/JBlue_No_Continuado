/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.modelo.operaciones.aux;

import com.jsql.conexion.Conexion;
import java.sql.ResultSet;

/**
 *
 * @author jp
 */
public abstract class Funciones {

    protected final Conexion cn;
    protected final String tabla;
    protected final String[] campos;

    public Funciones(String tabla, String[] campos) {
        this.cn = Conexion.getInstancia();
        this.tabla = tabla;
        this.campos = campos;
    }

    protected boolean insertar(String tabla, String campos[], String[] valores) {
        return cn.insert(tabla, cn.getCampos(campos), cn.getDatos(valores));
    }

    protected boolean eliminar(String tabla, String where) {
        return cn.delete(tabla, where);
    }

    protected boolean actualizar(String tabla, String campo, String valor, String where) {
        return cn.update(tabla, campo, valor, where);
    }

    protected boolean actualizar(String tabla, String campos[], String valores[], String where) {
        return cn.update(tabla, cn.getCampos_Datos(campos, valores), where);
    }

    protected ResultSet get(String tabla, String campos, String where) {
        return cn.select(tabla, campos, where);
    }

    public String[] getCampos() {
        return campos;
    }
    
}
