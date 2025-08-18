/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

/**
 *
 * @author omarf
 */
public class CalculadoraMenuGUI extends javax.swing.JFrame {

    /**
     * Creates new form CalculadoraMenuGUI
     */
    public CalculadoraMenuGUI() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Instancia de la clase estática para poder modificar la ventana del menú principal para los diferentes métodos
    static CalculadoraMenuGUI ventanaP = new CalculadoraMenuGUI();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCimiento1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        pnlOpciones = new javax.swing.JPanel();
        lblBienvenida = new javax.swing.JLabel();
        btnMuro = new javax.swing.JButton();
        btnLosa = new javax.swing.JButton();
        btnColumna = new javax.swing.JButton();
        btnCimiento = new javax.swing.JButton();
        lblTools = new javax.swing.JLabel();

        btnCimiento1.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        btnCimiento1.setText("Cimiento");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ConstructCalc");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlOpciones.setBackground(new java.awt.Color(75, 75, 75));
        pnlOpciones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblBienvenida.setFont(new java.awt.Font("Yu Gothic UI", 1, 18)); // NOI18N
        lblBienvenida.setForeground(java.awt.Color.white);
        lblBienvenida.setText("¡Calcule la cantidad de materiales que usted necesita!");
        pnlOpciones.add(lblBienvenida, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 0, 450, 28));

        btnMuro.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        btnMuro.setText("Muro");
        btnMuro.setBorderPainted(false);
        btnMuro.setFocusPainted(false);
        btnMuro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuroActionPerformed(evt);
            }
        });
        pnlOpciones.add(btnMuro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 140, -1));

        btnLosa.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        btnLosa.setText("Losa");
        btnLosa.setBorderPainted(false);
        btnLosa.setFocusPainted(false);
        btnLosa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLosaActionPerformed(evt);
            }
        });
        pnlOpciones.add(btnLosa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 140, -1));

        btnColumna.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        btnColumna.setText("Columna");
        btnColumna.setBorderPainted(false);
        btnColumna.setFocusPainted(false);
        btnColumna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColumnaActionPerformed(evt);
            }
        });
        pnlOpciones.add(btnColumna, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 140, -1));

        btnCimiento.setFont(new java.awt.Font("Yu Gothic UI", 1, 14)); // NOI18N
        btnCimiento.setText("Cimiento");
        btnCimiento.setBorderPainted(false);
        btnCimiento.setFocusPainted(false);
        btnCimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCimientoActionPerformed(evt);
            }
        });
        pnlOpciones.add(btnCimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 140, -1));

        lblTools.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/6193178.png"))); // NOI18N
        pnlOpciones.add(lblTools, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        getContentPane().add(pnlOpciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 260));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Al presionar el botón para calcuar el muro
    private void btnMuroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuroActionPerformed

        CalculadoraMuroGUI ventanaM = new CalculadoraMuroGUI();
        ventanaM.setVisible(true);
        ventanaM.setLocation(500, 200);
    }//GEN-LAST:event_btnMuroActionPerformed

    private void btnLosaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLosaActionPerformed

        CalculadoraLosaGUI ventanaL = new CalculadoraLosaGUI();
        ventanaL.setVisible(true);
        ventanaL.setLocation(500, 150);
    }//GEN-LAST:event_btnLosaActionPerformed

    private void btnColumnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColumnaActionPerformed

        CalculadoraColumnaGUI ventanaC = new CalculadoraColumnaGUI();
        ventanaC.setVisible(true);
        ventanaC.setLocation(450, 150);
    }//GEN-LAST:event_btnColumnaActionPerformed

    private void btnCimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCimientoActionPerformed

        CalculadoraCimientoGUI ventanaCI = new CalculadoraCimientoGUI();
        ventanaCI.setVisible(true);
        ventanaCI.setLocation(450, 150);
    }//GEN-LAST:event_btnCimientoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CalculadoraMenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CalculadoraMenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CalculadoraMenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CalculadoraMenuGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                ventanaP.setVisible(true);
                ventanaP.setLocation(550, 250);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCimiento;
    private javax.swing.JButton btnCimiento1;
    private javax.swing.JButton btnColumna;
    private javax.swing.JButton btnLosa;
    private javax.swing.JButton btnMuro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblBienvenida;
    private javax.swing.JLabel lblTools;
    private javax.swing.JPanel pnlOpciones;
    // End of variables declaration//GEN-END:variables
}
