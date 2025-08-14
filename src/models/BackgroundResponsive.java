/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Geison Medina
 */
public class BackgroundResponsive extends JPanel {
    private final Image background;

    public BackgroundResponsive(Image background) {
        this.background = background;
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Lo escala al tamaño actual del panel:
        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
    }
}

