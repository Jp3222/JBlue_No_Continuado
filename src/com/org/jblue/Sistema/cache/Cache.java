/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.Sistema.cache;

import com.org.jblue.Sistema.cache.cacheGUI.CacheGUI;

/**
 *
 * @author jp
 */
public abstract class Cache {

    private final CacheGUI cacheGUI;

    public Cache() {
        this.cacheGUI = CacheGUI.getCACHE_GUI();
    }

    public CacheGUI getCacheGUI() {
        return cacheGUI;
    }

}
