/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Geison Medina
 */
public class JtextTransparent extends JTextField {

    public JtextTransparent(int columns) {
        super(columns);
        // No usar el pintado de fondo por defecto
        setOpaque(false);
        // Color negro al 50% (128/255)
        setBackground(new Color(0, 0, 0, 128));
        setForeground(Color.WHITE); // o el color que prefieras para el texto
        setBorder(BorderFactory.createLineBorder(Color.WHITE)); // opcional: borde blanco
    }

    @Override
    protected void paintComponent(Graphics g) {
        // Pintamos el fondo semitransparente
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        // Luego, que el JTextField pinte el resto (cursor, texto, etc.)
        super.paintComponent(g);
    }
}
