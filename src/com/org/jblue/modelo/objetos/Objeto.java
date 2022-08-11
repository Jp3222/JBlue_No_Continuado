package com.org.jblue.modelo.objetos;

import java.util.Arrays;
import java.util.Objects;

/**
 *
 * @author jp
 */
public abstract class Objeto implements Comparable<Objeto>, Cloneable {

    private String id;
    private String[] info;
    private boolean existe;

    public Objeto(String[] info) {
        this.id = info[0];
        this.info = info;
        this.existe = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String[] getInfo() {
        return info;
    }

    public void setInfo(String[] info) {
        this.info = info;
        this.id = info[0];
        this.existe = true;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    @Override
    public int compareTo(Objeto t) {
        return id.compareTo(t.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.id);
        hash = 83 * hash + Arrays.deepHashCode(this.info);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Objeto other = (Objeto) obj;
        String[] a = Arrays.copyOfRange(this.info, 1, info.length);
        String[] b = Arrays.copyOfRange(other.info, 1, info.length);
        return Arrays.deepEquals(a, b);
    }

    @Override
    public String toString() {
        return Arrays.deepToString(info);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
