package com.org.jblue.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;

/**
 *
 * @author jp
 */
public abstract class Controlador {

    public abstract class Adaptador extends KeyAdapter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
        }
    }
}
