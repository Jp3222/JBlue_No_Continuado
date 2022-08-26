/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.controlador;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author jp
 */
public class MouseFunction extends MouseAdapter {

    private final ArrayList<MouseEventFunction> ListaMouseClicked;
    private final ArrayList<MouseEventFunction> ListaMousePressed;
    private final ArrayList<MouseEventFunction> ListaMouseReleased;

    public MouseFunction() {
        this.ListaMouseClicked = new ArrayList();
        this.ListaMousePressed = new ArrayList<>();
        this.ListaMouseReleased = new ArrayList<>();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (!ListaMouseClicked.isEmpty()) {
            for (MouseEventFunction mouseEventFunction : ListaMouseClicked) {
                mouseEventFunction.MouseEventFunction(e);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!ListaMousePressed.isEmpty()) {
            for (MouseEventFunction mouseEventFunction : ListaMousePressed) {
                mouseEventFunction.MouseEventFunction(e);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!ListaMouseReleased.isEmpty()) {
            for (MouseEventFunction mouseEventFunction : ListaMouseReleased) {
                mouseEventFunction.MouseEventFunction(e);
            }
        }
    }

    public void addMouseClickedEvent(MouseEventFunction e) {
        ListaMouseClicked.add(e);
    }

    public void addMousePressedEvent(MouseEventFunction e) {
        ListaMouseClicked.add(e);
    }

    public void addMouseReleasedEvent(MouseEventFunction e) {
        ListaMouseClicked.add(e);
    }
}
