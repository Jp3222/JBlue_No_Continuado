/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.controlador;

import com.org.jblue.Sistema.Cache;
import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.modelo.objetos.OTomas;
import com.org.jblue.modelo.objetos.OUsuarios;
import com.org.jblue.modelo.operaciones.OperacionesCalles;
import com.org.jblue.modelo.operaciones.OperacionesTomas;
import com.org.jblue.modelo.operaciones.OperacionesUsuarios;
import com.org.jblue.vistas.vistas.JPBaseDatos;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jp
 */
public class CBaseDatos extends Controlador {

    private final JPBaseDatos administracion;
    private final OperacionesTomas otomas;
    private final OperacionesCalles ocalles;
    private final OperacionesUsuarios ousuarios;
    private DefaultTableModel modelUsuarios, modelCalles, modelTomas;
    private final Cache memoriaCache;

    public CBaseDatos(JPBaseDatos administracion) {
        this.administracion = administracion;
        this.otomas = new OperacionesTomas();
        this.ocalles = new OperacionesCalles();
        this.ousuarios = new OperacionesUsuarios();
        this.memoriaCache = Cache.getInstancia();

    }

    // <editor-fold defaultstate="collapsed" desc="CRUD Tomas"> 
    //CRUD tomas
    public void addToma() {
        int i = administracion.camposVaciosTomas();
        if (i > 0) {
            JOptionPane.showMessageDialog(administracion, "Hay " + i + " campos vacios", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String[] o = administracion.getTomasInsert();
        if (otomas.insertar(o)) {
            memoriaCache.reCargarTomas();
            recargarTablaTomas();
            administracion.reiniciarListaCalles();
            JOptionPane.showMessageDialog(administracion, "Inserccion exitosa", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            administracion.vaciarCamposTomas();
        } else {
            JOptionPane.showMessageDialog(administracion, "Inserccion erronea", "Base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setTomas() {
        int i = administracion.camposVaciosTomas();
        if (i > 0) {
            JOptionPane.showMessageDialog(administracion, "Hay " + i + " campos vacios", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String[] o = administracion.getTomasUpdate();
        if (otomas.actualizar(otomas.getCampos(), o, "where id = " + o[0])) {
            memoriaCache.reCargarTomas();
            recargarTablaTomas();
            administracion.reiniciarListaTomas();
            JOptionPane.showMessageDialog(administracion, "Actualizacion exitosa", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            administracion.vaciarCamposTomas();
        } else {
            JOptionPane.showMessageDialog(administracion, "Actualizacion erronea", "Base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeTomas() {
        int i = administracion.camposVaciosTomas();
        if (i > 0) {
            JOptionPane.showMessageDialog(administracion, "Hay " + i + " campos vacios", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (otomas.eliminar("id = " + administracion.getTomasDelete())) {
            memoriaCache.reCargarTomas();
            recargarTablaTomas();
            administracion.reiniciarListaTomas();
            JOptionPane.showMessageDialog(administracion, "Eliminacion exitosa", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            administracion.vaciarCamposTomas();
        } else {
            JOptionPane.showMessageDialog(administracion, "Eliminacion erronea", "Base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    //</editor-fold>
//
    // <editor-fold defaultstate="collapsed" desc="CRUD Calles"> 
    //CRUD calles
    public void addCalle() {
        int i = administracion.campoVaciosCalles();
        if (i > 0) {
            JOptionPane.showMessageDialog(administracion, "Hay " + i + " campos vacios", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String[] valores = administracion.getCalleInsert();
        if (ocalles.insertar(valores)) {
            memoriaCache.reCargarCalles();
            recargarTablaCalles();
            administracion.reiniciarListaTomas();
            JOptionPane.showMessageDialog(administracion, "Inserccion exitosa", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            administracion.vaciarCamposCalles();
        } else {
            JOptionPane.showMessageDialog(administracion, "Inserccion erronea", "Base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setCalle() {
        int i = administracion.campoVaciosCalles();
        if (i > 0) {
            JOptionPane.showMessageDialog(administracion, "Hay " + i + " campos vacios", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String[] valores = administracion.getCalleInsert();
        if (ocalles.actualizar(ocalles.getCampos(), valores, "id = " + valores[0])) {
            memoriaCache.reCargarCalles();
            recargarTablaCalles();
            administracion.reiniciarListaTomas();
            JOptionPane.showMessageDialog(administracion, "Inserccion exitosa", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            administracion.vaciarCamposCalles();
        } else {
            JOptionPane.showMessageDialog(administracion, "Inserccion erronea", "Base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeCalle() {
        int i = administracion.campoVaciosCalles();
        if (i > 0) {
            JOptionPane.showMessageDialog(administracion, "Hay " + i + " campos vacios", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (ocalles.eliminar("id = " + administracion.getCallesDelete())) {
            memoriaCache.reCargarCalles();
            recargarTablaCalles();
            administracion.reiniciarListaTomas();
            JOptionPane.showMessageDialog(administracion, "Inserccion exitosa", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            administracion.vaciarCamposCalles();
        } else {
            JOptionPane.showMessageDialog(administracion, "Inserccion erronea", "Base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    //</editor-fold>
//
    // <editor-fold defaultstate="collapsed" desc="CRUD Usuarios"> 
    public void addUsuarios() {
        int i = administracion.camposVaciosUsuario();
        if (i > 0) {
            JOptionPane.showMessageDialog(administracion, "Hay " + i + " campos vacios", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String[] valores = administracion.getUsuariosInsert();
        if (ousuarios.insertar(valores)) {
            memoriaCache.reCargarUsuarios();
            recargarTablaUsuarios();
            JOptionPane.showMessageDialog(administracion, "Inserccion exitosa", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            administracion.vaciarCamposUsuario();
        } else {
            JOptionPane.showMessageDialog(administracion, "Inserccion erronea", "Base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void setUsuario() {
        int i = administracion.camposVaciosUsuario();
        if (i > 0) {
            JOptionPane.showMessageDialog(administracion, "Hay " + i + " campos vacios", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        String[] valores = administracion.getUsuariosUpdate();
        if (ousuarios.actualizar(ousuarios.getCampos(), valores, "id = " + valores[0])) {
            memoriaCache.reCargarUsuarios();
            recargarTablaUsuarios();
            JOptionPane.showMessageDialog(administracion, "Actualizacion exitosa", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            administracion.vaciarCamposUsuario();
        } else {
            JOptionPane.showMessageDialog(administracion, "Inserccion erronea", "Base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void removeUsuario() {
        if (ousuarios.eliminar("id = " + administracion.getUsuarioDelete())) {
            memoriaCache.reCargarUsuarios();
            recargarTablaUsuarios();
            JOptionPane.showMessageDialog(administracion, "Eliminacion exitosa", "Base de datos", JOptionPane.INFORMATION_MESSAGE);
            administracion.vaciarCamposUsuario();
        } else {
            JOptionPane.showMessageDialog(administracion, "Inserccion erronea", "Base de datos", JOptionPane.ERROR_MESSAGE);
        }
    }

    //</editor-fold>
//
    //cache
    public String getIDCalle(String n) {
        for (OCalles calle : memoriaCache.getCalles()) {
            String o = calle.getNombre() + " No. " + calle.getNumero();
            if (o.equals(n)) {
                return calle.getId();
            }
        }
        return "0";
    }

    public String getIDToma(String tipo) {
        for (OTomas toma : memoriaCache.getTomas()) {
            if (toma.getTipo().equalsIgnoreCase(tipo)) {
                return toma.getId();
            }
        }
        return "0";
    }

    //Tablas
    public void setModelCalles(DefaultTableModel modelCalles) {
        this.modelCalles = modelCalles;
    }

    public void llenarTablaCalles() {
        ArrayList<OCalles> calles = memoriaCache.getCalles();
        for (OCalles calle : calles) {
            modelCalles.addRow(calle.getInfo());
        }
    }

    public void vaciarTablaCalles() {
        while (modelCalles.getRowCount() > 0) {
            modelCalles.removeRow(0);
        }
    }

    public void recargarTablaCalles() {
        vaciarTablaCalles();
        llenarTablaCalles();
    }

    public void setModelTomas(DefaultTableModel modelTomas) {
        this.modelTomas = modelTomas;
    }

    public void llenarTablaTomas() {
        ArrayList<OTomas> tomas = memoriaCache.getTomas();
        for (OTomas toma : tomas) {
            System.out.println("toma");
            modelTomas.addRow(toma.getInfo());
        }
    }

    public void vaciarTablaTomas() {
        while (modelTomas.getRowCount() > 0) {
            modelTomas.removeRow(0);
        }
    }

    public void recargarTablaTomas() {
        vaciarTablaTomas();
        llenarTablaTomas();
    }

    public void setModelUsuarios(DefaultTableModel modelUsuarios) {
        this.modelUsuarios = modelUsuarios;
    }

    public void llenarTablaUsuarios() {
        ArrayList<OUsuarios> usuarios = memoriaCache.getUsuarios();
        for (OUsuarios o : usuarios) {
            modelUsuarios.addRow(o.getInfo());
        }
        ArrayList<OTomas> tomas = memoriaCache.getTomas();
        for (int i = 0; i < modelUsuarios.getRowCount(); i++) {
            String tipo = "" + modelUsuarios.getValueAt(i, 5);
            for (OTomas toma : tomas) {
                if (tipo.equalsIgnoreCase(toma.getId())) {
                    modelUsuarios.setValueAt(toma.getTipo(), i, 5);
                }
            }
        }
        ArrayList<OCalles> calles = memoriaCache.getCalles();
        for (int i = 0; i < modelUsuarios.getRowCount(); i++) {
            String tipo = "" + modelUsuarios.getValueAt(i, 4);
            for (OCalles calle : calles) {
                if (tipo.equals(calle.getId())) {
                    modelUsuarios.setValueAt(calle.getNombre() + " No. " + calle.getNumero(), i, 4);
                }
            }
        }
    }

    public void vaciarTablaUsuarios() {
        while (modelUsuarios.getRowCount() > 0) {
            modelUsuarios.removeRow(0);
        }
    }

    public void recargarTablaUsuarios() {
        vaciarTablaUsuarios();
        llenarTablaUsuarios();
    }

}
