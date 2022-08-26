/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache.cacheGUI;

import com.org.jblue.Sistema.cache.Cache;
import com.org.jblue.Sistema.cache.cacheGUI.cacheModelosTablas.CacheModeloTablas;

/**
 *
 * @author jp
 */
public class CacheGUI extends CacheModeloTablas {

    private static final CacheGUI CACHE_GUI = new CacheGUI();

    public static CacheGUI getCACHE_GUI() {
        return CACHE_GUI;
    }

    private CacheGUI() {

    }

}
