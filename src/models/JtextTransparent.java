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
public class JtextTransparent extends JTextField {

    /**
     * Crea un campo de texto transparente con el número de columnas indicado.
     *
     * @param columns número de columnas que tendrá el campo de texto
     */
    public JtextTransparent(int columns) {
        super(columns);
        // No usar el pintado de fondo por defecto
        setOpaque(false);
        // Fondo negro semitransparente (128/255 de opacidad)
        setBackground(new Color(0, 0, 0, 128));
        // Color del texto
        setForeground(Color.WHITE);
        // Borde blanco opcional
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }

    /**
     * Sobrescribe el método para pintar un fondo semitransparente antes de que
     * el {@link JTextField} pinte el texto, el cursor y demás elementos.
     *
     * @param g objeto gráfico para renderizar
     */
    @Override
    protected void paintComponent(Graphics g) {
        // Pintamos el fondo semitransparente
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(getBackground());
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.dispose();
        // Luego el JTextField pinta el texto y el cursor
        super.paintComponent(g);
    }
}
