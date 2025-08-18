/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import conectDB.UsuarioDAO;
import java.sql.ResultSet;
import java.sql.Statement;
import conectDB.conectMysql;
import java.sql.Connection;
import java.sql.SQLException;
import models.Usuario;
import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author Geison Medina
 */
public class Register extends javax.swing.JFrame {

    conectMysql con = new conectMysql();
    Connection cn = con.conectar();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Register.class.getName());
    private final UsuarioDAO usuarioDAO;
    private final String PLACEHOLDER_NOMBRES = "Nombre y Apellido";
    private final String PLACEHOLDER_CORREO = "Correo";
    private final String PLACEHOLDER_PASSWORD = "Contraseña";
    private final String PLACEHOLDER_PASSWORD_CONFIRM = "Confirmar contraseña";
    private final String PLACEHOLDER_TELEFONO = "(   )   -   ";

    /**
     * Creates new form Register
     */
    public Register() {
        initComponents();
        this.setLocationRelativeTo(null);
        showData();
        usuarioDAO = new UsuarioDAO();
        configurarMascaraTelefono();
        configurarPlaceholders();
        configurarEventos();

        setResizable(false);       // desactiva redimensionar y con ello el botón maximizar
// opcional: fija tamaño exacto
        setSize(1280, 730);
        setMinimumSize(getSize());
        setMaximumSize(getSize()); // así nadie lo cambia por código
    }

    private void configurarEventos() {
        buttonRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registrarUsuario();
            }
        });
        enlaceBackRegister.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                new Login().setVisible(true);
                dispose();
            }
        });
    }

    private void configurarMascaraTelefono() {
        try {
            MaskFormatter maskTelefono = new MaskFormatter("(###) ###-####");
            maskTelefono.setPlaceholderCharacter(' ');
            maskTelefono.install(inputTelRegister);

            inputTelRegister.setForeground(new Color(153, 153, 153));
        } catch (ParseException e) {
            logger.log(java.util.logging.Level.WARNING, "Error al configurar máscara de teléfono", e);
        }
    }

    private void configurarPlaceholders() {
        // Configurar placeholder para nombres
        inputNamesRegister.setText(PLACEHOLDER_NOMBRES);
        inputNamesRegister.setForeground(new Color(153, 153, 153));
        inputNamesRegister.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputNamesRegister.getText().equals(PLACEHOLDER_NOMBRES)) {
                    inputNamesRegister.setText("");
                    inputNamesRegister.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputNamesRegister.getText().isEmpty()) {
                    inputNamesRegister.setText(PLACEHOLDER_NOMBRES);
                    inputNamesRegister.setForeground(new Color(153, 153, 153));
                }
            }
        });

        // Configurar placeholder para correo
        inputCorreoRegister.setText(PLACEHOLDER_CORREO);
        inputCorreoRegister.setForeground(new Color(153, 153, 153));
        inputCorreoRegister.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputCorreoRegister.getText().equals(PLACEHOLDER_CORREO)) {
                    inputCorreoRegister.setText("");
                    inputCorreoRegister.setForeground(Color.WHITE);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputCorreoRegister.getText().isEmpty()) {
                    inputCorreoRegister.setText(PLACEHOLDER_CORREO);
                    inputCorreoRegister.setForeground(new Color(153, 153, 153));
                }
            }
        });

        // Configurar placeholder para contraseña
        inputPasswordRegister.setText(PLACEHOLDER_PASSWORD);
        inputPasswordRegister.setForeground(new Color(153, 153, 153));
        inputPasswordRegister.setEchoChar((char) 0); // Mostrar texto como placeholder
        inputPasswordRegister.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String currentText = new String(inputPasswordRegister.getPassword());
                if (currentText.equals(PLACEHOLDER_PASSWORD)) {
                    inputPasswordRegister.setText("");
                    inputPasswordRegister.setForeground(Color.WHITE);
                    inputPasswordRegister.setEchoChar('*'); // Ocultar contraseña
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String currentText = new String(inputPasswordRegister.getPassword());
                if (currentText.isEmpty()) {
                    inputPasswordRegister.setText(PLACEHOLDER_PASSWORD);
                    inputPasswordRegister.setForeground(new Color(153, 153, 153));
                    inputPasswordRegister.setEchoChar((char) 0); // Mostrar placeholder
                }
            }
        });

        // Configurar placeholder para confirmar contraseña
        inputPasswordConfirmRegister.setText(PLACEHOLDER_PASSWORD_CONFIRM);
        inputPasswordConfirmRegister.setForeground(new Color(153, 153, 153));
        inputPasswordConfirmRegister.setEchoChar((char) 0); // Mostrar texto como placeholder
        inputPasswordConfirmRegister.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String currentText = new String(inputPasswordConfirmRegister.getPassword());
                if (currentText.equals(PLACEHOLDER_PASSWORD_CONFIRM)) {
                    inputPasswordConfirmRegister.setText("");
                    inputPasswordConfirmRegister.setForeground(Color.WHITE);
                    inputPasswordConfirmRegister.setEchoChar('*'); // Ocultar contraseña
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String currentText = new String(inputPasswordConfirmRegister.getPassword());
                if (currentText.isEmpty()) {
                    inputPasswordConfirmRegister.setText(PLACEHOLDER_PASSWORD_CONFIRM);
                    inputPasswordConfirmRegister.setForeground(new Color(153, 153, 153));
                    inputPasswordConfirmRegister.setEchoChar((char) 0); // Mostrar placeholder
                }
            }
        });

        // Configurar placeholder para teléfono
        inputTelRegister.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                inputTelRegister.setForeground(Color.WHITE);
            }

            @Override
            public void focusLost(FocusEvent e) {
                String telefono = inputTelRegister.getText().replaceAll("[\\s()\\-]", "");
                if (telefono.isEmpty()) {
                    inputTelRegister.setForeground(new Color(153, 153, 153));
                }
            }
        });
    }

    private void registrarUsuario() {
        String fullName = inputNamesRegister.getText().trim();
        String correo = inputCorreoRegister.getText().trim();
        String password = new String(inputPasswordRegister.getPassword()).trim();
        String passwordConfirm = new String(inputPasswordConfirmRegister.getPassword()).trim();
        String telefono = inputTelRegister.getText().trim();

        // Validar campos vacíos
        if (fullName.isEmpty() || correo.isEmpty() || password.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar formato de correo
        if (!usuarioDAO.esCorreoValido(correo)) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese un correo válido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar que las contraseñas coincidan
        if (!password.equals(passwordConfirm)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validar longitud mínima de contraseña
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(this, "La contraseña debe tener al menos 8 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si el correo ya existe
        if (usuarioDAO.correoExiste(correo)) {
            JOptionPane.showMessageDialog(this, "El correo ya está registrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Usuario nuevoUsuario = new Usuario(fullName, correo, password, telefono);
        if (usuarioDAO.registrarUsuario(nuevoUsuario)) {
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente");
            limpiarCampos();
            new Login().setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        inputNamesRegister.setText(PLACEHOLDER_NOMBRES);
        inputNamesRegister.setForeground(new Color(153, 153, 153));

        inputCorreoRegister.setText(PLACEHOLDER_CORREO);
        inputCorreoRegister.setForeground(new Color(153, 153, 153));

        inputPasswordRegister.setText(PLACEHOLDER_PASSWORD);
        inputPasswordRegister.setForeground(new Color(153, 153, 153));
        inputPasswordRegister.setEchoChar((char) 0);

        inputPasswordConfirmRegister.setText(PLACEHOLDER_PASSWORD_CONFIRM);
        inputPasswordConfirmRegister.setForeground(new Color(153, 153, 153));
        inputPasswordConfirmRegister.setEchoChar((char) 0);

        inputTelRegister.setText("");
        inputTelRegister.setForeground(new Color(153, 153, 153));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        enlaceBackRegister = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        buttonRegister = new javax.swing.JLabel();
        inputCorreoRegister = new javax.swing.JTextField();
        inputNamesRegister = new javax.swing.JTextField();
        inputTelRegister = new javax.swing.JFormattedTextField();
        inputPasswordConfirmRegister = new javax.swing.JPasswordField();
        inputPasswordRegister = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        enlaceBackRegister.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        enlaceBackRegister.setForeground(new java.awt.Color(255, 255, 255));
        enlaceBackRegister.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enlaceBackRegister.setText("Volver a Accesso");
        enlaceBackRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(enlaceBackRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 630, -1, -1));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 650, 120, 20));

        buttonRegister.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        buttonRegister.setForeground(new java.awt.Color(255, 255, 255));
        buttonRegister.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buttonRegister.setText("Registrar");
        buttonRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(buttonRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 562, 300, 50));

        inputCorreoRegister.setBackground(new java.awt.Color(0, 0, 0, 0));
        inputCorreoRegister.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        inputCorreoRegister.setForeground(new java.awt.Color(153, 153, 153));
        inputCorreoRegister.setText("Correo");
        inputCorreoRegister.setBorder(null);
        inputCorreoRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputCorreoRegisterActionPerformed(evt);
            }
        });
        jPanel1.add(inputCorreoRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 260, 290, 30));

        inputNamesRegister.setBackground(new java.awt.Color(0, 0, 0, 0));
        inputNamesRegister.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        inputNamesRegister.setForeground(new java.awt.Color(153, 153, 153));
        inputNamesRegister.setText("Nombre y Apellido");
        inputNamesRegister.setBorder(null);
        inputNamesRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNamesRegisterActionPerformed(evt);
            }
        });
        jPanel1.add(inputNamesRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 182, 290, 30));

        inputTelRegister.setBackground(new java.awt.Color(0, 0, 0, 0));
        inputTelRegister.setBorder(null);
        inputTelRegister.setForeground(new java.awt.Color(153, 153, 153));
        inputTelRegister.setText("Telefono");
        inputTelRegister.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jPanel1.add(inputTelRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 500, 290, 30));

        inputPasswordConfirmRegister.setBackground(new java.awt.Color(0, 0, 0, 0));
        inputPasswordConfirmRegister.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        inputPasswordConfirmRegister.setForeground(new java.awt.Color(153, 153, 153));
        inputPasswordConfirmRegister.setText("jPasswordField1");
        inputPasswordConfirmRegister.setBorder(null);
        jPanel1.add(inputPasswordConfirmRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 420, 290, 30));

        inputPasswordRegister.setBackground(new java.awt.Color(0, 0, 0, 0));
        inputPasswordRegister.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        inputPasswordRegister.setForeground(new java.awt.Color(153, 153, 153));
        inputPasswordRegister.setText("jPasswordField1");
        inputPasswordRegister.setBorder(null);
        jPanel1.add(inputPasswordRegister, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 340, 290, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/Register.gif"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputCorreoRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputCorreoRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputCorreoRegisterActionPerformed

    private void inputNamesRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNamesRegisterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNamesRegisterActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel buttonRegister;
    private javax.swing.JLabel enlaceBackRegister;
    private javax.swing.JTextField inputCorreoRegister;
    private javax.swing.JTextField inputNamesRegister;
    private javax.swing.JPasswordField inputPasswordConfirmRegister;
    private javax.swing.JPasswordField inputPasswordRegister;
    private javax.swing.JFormattedTextField inputTelRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    // End of variables declaration//GEN-END:variables

    private void showData() {
        String consultaSQL = "select * from users";
        System.out.println();
        String data[] = new String[6];

        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(consultaSQL);
            while (rs.next()) {
                data[0] = rs.getString(2);
            }

            System.err.println(data[0] + "nada");
        } catch (SQLException e) {
            System.out.println("Error al mostrar Datos " + e);
        }
    }
}
