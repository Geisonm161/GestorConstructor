/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import conectDB.UsuarioDAO;
import models.Usuario;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JOptionPane;

/**
 *
 * @author Geison Medina
 */
public class Login extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Login.class.getName());
    private UsuarioDAO usuarioDAO;
    private final String CORREO_PLACEHOLDER = "Correo";
    private final String PASSWORD_PLACEHOLDER = "Contraseña";

    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        this.setLocationRelativeTo(null);
        usuarioDAO = new UsuarioDAO();
        configurarCamposConPlaceholders();

        // Acción clic en el enlace de registro
        enlaceRegisterLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Register().setVisible(true);
                dispose(); // Cierra la ventana de login
            }
        });

        // Acción clic en el botón de iniciar sesión
        buttonInitLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iniciarSesion();
            }
        });
    }

    /**
     * Configuracion de campos de texto con placeholders
     */
    private void configurarCamposConPlaceholders() {
        // Configurar campo de correo
        inputCorreoLogin.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputCorreoLogin.getText().equals(CORREO_PLACEHOLDER)) {
                    inputCorreoLogin.setText("");
                    inputCorreoLogin.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputCorreoLogin.getText().trim().isEmpty()) {
                    inputCorreoLogin.setText(CORREO_PLACEHOLDER);
                    inputCorreoLogin.setForeground(new Color(153, 153, 153));
                }
            }
        });

        // Configurar campo de contraseña
        inputPasswordLogin.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String passwordText = new String(inputPasswordLogin.getPassword());
                if (passwordText.equals(PASSWORD_PLACEHOLDER)) {
                    inputPasswordLogin.setText("");
                    inputPasswordLogin.setForeground(Color.WHITE);
                    inputPasswordLogin.setEchoChar('•'); // Mostrar caracteres de contraseña
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String passwordText = new String(inputPasswordLogin.getPassword());
                if (passwordText.trim().isEmpty()) {
                    inputPasswordLogin.setText(PASSWORD_PLACEHOLDER);
                    inputPasswordLogin.setForeground(new Color(153, 153, 153));
                    inputPasswordLogin.setEchoChar((char) 0); // Mostrar texto plano para placeholder
                }
            }
        });

        // Inicializar estados
        inputCorreoLogin.setText(CORREO_PLACEHOLDER);
        inputCorreoLogin.setForeground(new Color(153, 153, 153));

        inputPasswordLogin.setText(PASSWORD_PLACEHOLDER);
        inputPasswordLogin.setForeground(new Color(153, 153, 153));
        inputPasswordLogin.setEchoChar((char) 0); // Sin caracteres de contraseña para placeholder
    }

    private void iniciarSesion() {
        String correo = inputCorreoLogin.getText().trim();
        String password = new String(inputPasswordLogin.getPassword()).trim();

        // Verificar si los campos contienen placeholders o están vacíos
        if (correo.isEmpty() || correo.equals(CORREO_PLACEHOLDER)
                || password.isEmpty() || password.equals(PASSWORD_PLACEHOLDER)) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!usuarioDAO.esCorreoValido(correo)) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un correo válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario usuario = usuarioDAO.autenticarUsuario(correo, password);
        if (usuario != null) {
            JOptionPane.showMessageDialog(this, "Bienvenido " + usuario.getFullName());
            new Shopping(usuario).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableJob = new javax.swing.JPanel();
        inputCorreoLogin = new javax.swing.JTextField();
        enlaceRegisterLogin = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        buttonInitLogin = new javax.swing.JLabel();
        inputPasswordLogin = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tableJob.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        inputCorreoLogin.setBackground(new java.awt.Color(0, 0, 0,0));
        inputCorreoLogin.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        inputCorreoLogin.setForeground(new java.awt.Color(153, 153, 153));
        inputCorreoLogin.setText("Correo");
        inputCorreoLogin.setBorder(null);
        inputCorreoLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputCorreoLoginActionPerformed(evt);
            }
        });
        tableJob.add(inputCorreoLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 320, 290, 30));

        enlaceRegisterLogin.setBackground(new java.awt.Color(51, 51, 255));
        enlaceRegisterLogin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        enlaceRegisterLogin.setForeground(new java.awt.Color(204, 255, 255));
        enlaceRegisterLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enlaceRegisterLogin.setText("¿No tienes cuenta? Regístrate");
        enlaceRegisterLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        enlaceRegisterLogin.setFocusCycleRoot(true);
        enlaceRegisterLogin.setFocusTraversalPolicyProvider(true);
        tableJob.add(enlaceRegisterLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 560, 280, -1));
        tableJob.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 580, 190, 20));

        buttonInitLogin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        buttonInitLogin.setForeground(new java.awt.Color(255, 255, 255));
        buttonInitLogin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonInitLogin.setText("Iniciar sesión");
        buttonInitLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        buttonInitLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                buttonInitLoginKeyPressed(evt);
            }
        });
        tableJob.add(buttonInitLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 480, 300, 60));

        inputPasswordLogin.setBackground(new java.awt.Color(0, 0, 0, 0));
        inputPasswordLogin.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        inputPasswordLogin.setForeground(new java.awt.Color(153, 153, 153));
        inputPasswordLogin.setText("jPasswordField1");
        inputPasswordLogin.setBorder(null);
        tableJob.add(inputPasswordLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 400, 290, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/marco9.gif"))); // NOI18N
        tableJob.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableJob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tableJob, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputCorreoLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputCorreoLoginActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputCorreoLoginActionPerformed

    private void buttonInitLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonInitLoginKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_buttonInitLoginKeyPressed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Login().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel buttonInitLogin;
    private javax.swing.JLabel enlaceRegisterLogin;
    private javax.swing.JTextField inputCorreoLogin;
    private javax.swing.JPasswordField inputPasswordLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPanel tableJob;
    // End of variables declaration//GEN-END:variables
}
