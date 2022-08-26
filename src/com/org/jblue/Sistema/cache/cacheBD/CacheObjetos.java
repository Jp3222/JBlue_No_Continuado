/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheBD;

import com.org.jblue.modelo.objetos.OCalles;
import com.org.jblue.modelo.objetos.OConsumidores;
import com.org.jblue.modelo.objetos.OTitulares;
import com.org.jblue.modelo.objetos.OTomas;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class CacheObjetos {

    public final ListaTitulares CACHE_TITULARES = new ListaTitulares();
    public final ListaConsumidores CACHE_CONSUMIDORES = new ListaConsumidores();
    public final ListaTomas CACHE_TOMAS = new ListaTomas();
    public final ListaCalles CACHE_CALLES = new ListaCalles();

    public abstract class CACHE_SUPER implements FuncionesCache {

    }

    public class ListaTitulares extends CACHE_SUPER {

        private final CacheLista<OTitulares> cache;

        public ListaTitulares() {
            this.cache = new CacheLista("titulares", new OTitulares());
        }

        @Override
        public void cargar() {
            cache.cargar();
        }

        @Override
        public void vaciar() {
            cache.vaciar();
        }

        @Override
        public void recargar() {
            cache.recargar();
        }

        public ArrayList<OTitulares> get() {
            return cache.getLista();
        }
    }

    public class ListaConsumidores extends CACHE_SUPER {

        private final CacheLista<OConsumidores> cache;

        public ListaConsumidores() {
            this.cache = new CacheLista("consumidores", new OConsumidores());
        }

        @Override
        public void cargar() {
            cache.cargar();
        }

        @Override
        public void vaciar() {
            cache.vaciar();
        }

        @Override
        public void recargar() {
            cache.recargar();
        }

        public ArrayList<OConsumidores> get() {
            return cache.getLista();
        }

    }

    public class ListaTomas extends CACHE_SUPER {

        private final CacheLista<OTomas> cache;

        public ListaTomas() {
            this.cache = new CacheLista("tomas", new OTomas());
        }

        @Override
        public void cargar() {
            cache.cargar();
        }

        @Override
        public void vaciar() {
            cache.vaciar();
        }

        @Override
        public void recargar() {
            cache.recargar();
        }

        public ArrayList<OTomas> get() {
            return cache.getLista();
        }
    }

    public class ListaCalles extends CACHE_SUPER {

        private final CacheLista<OCalles> cache;

        public ListaCalles() {
            this.cache = new CacheLista("calles", new OCalles());
        }

        @Override
        public void cargar() {
            cache.cargar();
        }

        @Override
        public void vaciar() {
            cache.vaciar();
        }

        @Override
        public void recargar() {
            cache.recargar();
        }

        public ArrayList<OCalles> get() {
            return cache.getLista();
        }
    }

}
