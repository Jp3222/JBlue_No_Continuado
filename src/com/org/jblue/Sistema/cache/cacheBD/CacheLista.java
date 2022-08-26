/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheBD;

import com.jsql.conexion.Conexion;
import com.org.jblue.Const;
import com.org.jblue.modelo.objetos.Objeto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jp
 * @param <T>
 */
public class CacheLista<T extends Objeto> implements FuncionesCache {

    private final ArrayList<T> lista;
    private final Conexion cn;
    private final String tabla;
    private final T o;

    public CacheLista(String tabla, T o) {
        this.lista = new ArrayList<>();
        this.cn = Conexion.getInstancia();
        this.tabla = tabla;
        this.o = o;
    }

    public ArrayList<T> getLista() {
        return lista;
    }

    @Override
    public void cargar() {
        try {
            try ( ResultSet select = cn.select(tabla)) {
                String[] campos = Const.BD_TITULARES;
                int tamanio = campos.length;
                while (select.next()) {
                    String[] array = getArray(select, tamanio, campos);
                    this.o.setInfo(array);
                    this.lista.add((T) this.o.clone());
                }
            }
        } catch (SQLException | CloneNotSupportedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void vaciar() {
        lista.clear();
    }

    @Override
    public void recargar() {
        vaciar();
        cargar();
    }

    private String[] getArray(ResultSet rs, int tamanio, String[] campos) throws SQLException {
        String[] array = new String[tamanio];
        int i = 0;
        for (String campo : campos) {
            array[i] = rs.getString(campo);
            i++;
        }
        return array;
    }

}
