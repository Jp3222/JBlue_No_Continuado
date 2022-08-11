/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.org.jblue.vistas.vistas.componentes;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/**
 *
 * @author jp
 */
public class DecoradorBotones implements MouseListener {

    private final JButton button;

    private final Color click_back;
    private final Color hover_back;
    private final Color normal_back;

    private final Color click_font;
    private final Color hover_font;
    private final Color normal_font;

    public DecoradorBotones(JButton button) {
        this.button = button;
        this.click_back = Color.gray;
        this.hover_back = Color.lightGray;
        this.normal_back = Color.gray;
        this.click_font = Color.black;
        this.hover_font = Color.white;
        this.normal_font = Color.black;
    }

    @Override
    public void mouseClicked(MouseEvent me) {

    }

    @Override
    public void mousePressed(MouseEvent me) {
        this.button.setBackground(click_back);
    }

    @Override
    public void mouseReleased(MouseEvent me) {

    }

    @Override
    public void mouseEntered(MouseEvent me) {
        this.button.setBackground(hover_back);
    }

    @Override
    public void mouseExited(MouseEvent me) {
        this.button.setBackground(normal_back);
    }

}
