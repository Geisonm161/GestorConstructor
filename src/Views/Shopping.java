/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Views;

import java.sql.Connection;
import conectDB.ProductDAO;
import conectDB.conectMysql;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;
import models.Usuario;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import models.Product;

/**
 *
 * @author Geison Medina
 */
public class Shopping extends javax.swing.JFrame {

    private final conectMysql conexion = new conectMysql();
    private Connection cn;

    /**
     * Creates new form Shopping
     *
     * @param userInfo
     */
    public Shopping(Usuario userInfo) {
        initComponents();
        this.setLocationRelativeTo(null);
        inputNameShow.setText(userInfo.getFullName());
        inputTelShow.setText("Tel: " + userInfo.getTelefono());
        handleDataInfoProduct();
        logOut.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                handleActionLogOut();
            }
        });
    }

    private void handleDataInfoProduct() {
        cn = conexion.conectar();
        if (cn == null) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo conectar a la base de datos.",
                    "Error de conexión",
                    JOptionPane.ERROR_MESSAGE);
            return; // ⬅️ salimos si no hay conexión
        }

        ProductDAO dao = new ProductDAO(cn);

        try {
            List<Product> productos = dao.findAll();

            // --- Arena (category_id = 2)
            List<Product> productosArena = productos.stream()
                    .filter(p -> p.getCategoryId() == 2)
                    .limit(4)
                    .collect(Collectors.toList());

            JLabel[] labelsArenaName = {arenaNameOne, arenaNameTwo, arenaNameThree, arenaNameFour};
            JLabel[] labelsArenaPrice = {arenaPriceOne, arenaPriceTwo, arenaPriceThree, arenaPriceFour};
            JLabel[] labelsArenaImage = {arenaImageOne, arenaImageTwo, arenaImageThree, arenaImageFour};
            JLabel[] labelsArenaDescription = { arenaDescriptionOne, arenaDescriptionTwo, arenaDescriptionThree, arenaDescriptionFour};
            JLabel[] labelsArenaStoreName = {storeNameOne, storeNameTwo, storeNameThree, storeNameFour};
            JLabel[] labelsArenaAddress = {addressOne, addressTwo, addressThree, addressFour};

            fillCards(productosArena, labelsArenaName, labelsArenaPrice, labelsArenaImage,
                    labelsArenaDescription, labelsArenaStoreName, labelsArenaAddress);

            // --- Cemento (category_id = 1)
            List<Product> productosCemento = productos.stream()
                    .filter(p -> p.getCategoryId() == 1)
                    .limit(4)
                    .collect(Collectors.toList());

            JLabel[] labelsCementoName = {cementoNameOne, cementoNameTwo, cementoNameThree, cementoNameFour};
            JLabel[] labelsCementoPrice = {cementoPriceOne, cementoPriceTwo, cementoPriceThree, cementoPriceFour}; // ⬅️ NO reutilizar los de Arena
            JLabel[] labelsCementoImage = {cementoImageOne, cementoImageTwo, cementoImageThree, cementoImageFour};
            JLabel[] labelsCementoDescription = {cementoDescriptionOne, cementoDescriptionTwo, cementoDescriptionThree, cementoDescriptionFour};
            JLabel[] labelsCementoStoreName = {cementoStoreNameOne, cementoStoreNameTwo, cementoStoreNameThree, cementoStoreNameFour};
            JLabel[] labelsCementoAddress = {cementoAddressStoreOne, cementoAddressStoreTwo, cementoAddressStoreThree, cementoAddressStoreFour};

            fillCards(productosCemento, labelsCementoName, labelsCementoPrice, labelsCementoImage,
                    labelsCementoDescription, labelsCementoStoreName, labelsCementoAddress);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al consultar productos: " + e.getMessage(),
                    "DB Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Rellena hasta 4 tarjetas de producto con imagen segura y formato de
     * precio.
     */
    private void fillCards(List<Product> items,
            JLabel[] lblName, JLabel[] lblPrice, JLabel[] lblImage,
            JLabel[] lblDesc, JLabel[] lblStore, JLabel[] lblAddr) {

        int n = Math.min(4, items.size());
        for (int i = 0; i < n; i++) {
            Product p = items.get(i);

            // Imagen con fallback
            String base = (p.getImagePath() == null || p.getImagePath().isEmpty())
                    ? "default" : p.getImagePath();
            URL url = getClass().getResource("/Img/" + base + ".gif");
            if (url == null) {
                url = getClass().getResource("/Img/default.gif");
            }
            if (url != null) {
                lblImage[i].setIcon(new ImageIcon(url));
            } else {
                lblImage[i].setIcon(null); // último recurso
            }

            lblName[i].setText(p.getProductName());
            lblPrice[i].setText( "RD$" + p.getPrice()  + " " + p.getUnit());
            lblDesc[i].setText(p.getProductDescription());
            lblStore[i].setText("Sucursal: " + (p.getHardwareStoreName() == null ? "-" : p.getHardwareStoreName()));
            lblAddr[i].setText("Dirección: " + (p.getAddress() == null ? "-" : p.getAddress()));
        }
    }

    private void handleActionLogOut() {
        int opcion = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro que deseas cerrar sesión?",
                "Confirmar cierre de sesión",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (opcion == JOptionPane.YES_OPTION) {
            // Acción si el usuario elige "Sí"
            System.out.println("Cerrando sesión...");
            dispose();
            new Login().setVisible(true);
        } else if (opcion == JOptionPane.NO_OPTION) {
            // Acción si el usuario elige "No"
            System.out.println("Cancelado por el usuario.");
        }

    }

    ;

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRound5 = new models.PanelRound();
        jLabel8 = new javax.swing.JLabel();
        panelRound17 = new models.PanelRound();
        panelRound18 = new models.PanelRound();
        panelRound19 = new models.PanelRound();
        panelRound20 = new models.PanelRound();
        jSeparator4 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        panelRound1 = new models.PanelRound();
        jLabel5 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        panelRound4 = new models.PanelRound();
        arenaImageOne = new javax.swing.JLabel();
        arenaNameOne = new javax.swing.JLabel();
        arenaDescriptionOne = new javax.swing.JLabel();
        storeNameOne = new javax.swing.JLabel();
        arenaPriceOne = new javax.swing.JLabel();
        addressOne = new javax.swing.JLabel();
        panelRound6 = new models.PanelRound();
        arenaImageTwo = new javax.swing.JLabel();
        arenaNameTwo = new javax.swing.JLabel();
        arenaDescriptionTwo = new javax.swing.JLabel();
        arenaPriceTwo = new javax.swing.JLabel();
        storeNameTwo = new javax.swing.JLabel();
        addressTwo = new javax.swing.JLabel();
        panelRound7 = new models.PanelRound();
        arenaImageThree = new javax.swing.JLabel();
        arenaNameThree = new javax.swing.JLabel();
        arenaDescriptionThree = new javax.swing.JLabel();
        arenaPriceThree = new javax.swing.JLabel();
        storeNameThree = new javax.swing.JLabel();
        addressThree = new javax.swing.JLabel();
        panelRound8 = new models.PanelRound();
        arenaImageFour = new javax.swing.JLabel();
        arenaNameFour = new javax.swing.JLabel();
        arenaDescriptionFour = new javax.swing.JLabel();
        arenaPriceFour = new javax.swing.JLabel();
        storeNameFour = new javax.swing.JLabel();
        addressFour = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        panelRound9 = new models.PanelRound();
        cementoImageOne = new javax.swing.JLabel();
        cementoNameOne = new javax.swing.JLabel();
        cementoPriceOne = new javax.swing.JLabel();
        cementoDescriptionOne = new javax.swing.JLabel();
        cementoStoreNameOne = new javax.swing.JLabel();
        cementoAddressStoreOne = new javax.swing.JLabel();
        panelRound10 = new models.PanelRound();
        cementoImageTwo = new javax.swing.JLabel();
        cementoNameTwo = new javax.swing.JLabel();
        cementoPriceTwo = new javax.swing.JLabel();
        cementoDescriptionTwo = new javax.swing.JLabel();
        cementoStoreNameTwo = new javax.swing.JLabel();
        cementoAddressStoreTwo = new javax.swing.JLabel();
        panelRound11 = new models.PanelRound();
        cementoImageThree = new javax.swing.JLabel();
        cementoNameThree = new javax.swing.JLabel();
        cementoPriceThree = new javax.swing.JLabel();
        cementoDescriptionThree = new javax.swing.JLabel();
        cementoStoreNameThree = new javax.swing.JLabel();
        cementoAddressStoreThree = new javax.swing.JLabel();
        panelRound12 = new models.PanelRound();
        cementoImageFour = new javax.swing.JLabel();
        cementoNameFour = new javax.swing.JLabel();
        cementoPriceFour = new javax.swing.JLabel();
        cementoDescriptionFour = new javax.swing.JLabel();
        cementoStoreNameFour = new javax.swing.JLabel();
        cementoAddressStoreFour = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        panelRound13 = new models.PanelRound();
        concretoImageOne = new javax.swing.JLabel();
        concretoNameOne = new javax.swing.JLabel();
        concretoPriceOne = new javax.swing.JLabel();
        concretoDescriptionOne = new javax.swing.JLabel();
        concretoStoreNameOne = new javax.swing.JLabel();
        concretoAddressStoreOne = new javax.swing.JLabel();
        panelRound14 = new models.PanelRound();
        concretoImageTwo = new javax.swing.JLabel();
        concretoNameTwo = new javax.swing.JLabel();
        concretoPriceTwo = new javax.swing.JLabel();
        concretoDescriptionTwo = new javax.swing.JLabel();
        concretoStoreNameTwo = new javax.swing.JLabel();
        concretoAddressStoreTwo = new javax.swing.JLabel();
        panelRound15 = new models.PanelRound();
        concretoImageThree = new javax.swing.JLabel();
        concretoNameThree = new javax.swing.JLabel();
        concretoPriceThree = new javax.swing.JLabel();
        concretoDescriptionThree = new javax.swing.JLabel();
        concretoStoreNameThree = new javax.swing.JLabel();
        concretoAddressStoreThree = new javax.swing.JLabel();
        panelRound16 = new models.PanelRound();
        concretoImageFour = new javax.swing.JLabel();
        concretoNameFour = new javax.swing.JLabel();
        concretoPriceFour = new javax.swing.JLabel();
        concretoDescriptionFour = new javax.swing.JLabel();
        concretoStoreNameFour = new javax.swing.JLabel();
        concretoAddressStoreFour = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        panelRound21 = new models.PanelRound();
        varillaImageOne = new javax.swing.JLabel();
        varillaNameOne = new javax.swing.JLabel();
        varillaPriceOne = new javax.swing.JLabel();
        varillaDescriptionOne = new javax.swing.JLabel();
        varillaStoreNameOne = new javax.swing.JLabel();
        varillaAddressStoreOne = new javax.swing.JLabel();
        panelRound22 = new models.PanelRound();
        varillaImageTwo = new javax.swing.JLabel();
        varillaNameTwo = new javax.swing.JLabel();
        varillaPriceTwo = new javax.swing.JLabel();
        varillaDescriptionTwo = new javax.swing.JLabel();
        varillaStoreNameTwo = new javax.swing.JLabel();
        varillaAddressStoreTwo = new javax.swing.JLabel();
        panelRound23 = new models.PanelRound();
        varillaImageThree = new javax.swing.JLabel();
        varillaNameThree = new javax.swing.JLabel();
        varillaPriceThree = new javax.swing.JLabel();
        varillaDescriptionThree = new javax.swing.JLabel();
        varillaStoreNameThree = new javax.swing.JLabel();
        varillaAddressStoreThree = new javax.swing.JLabel();
        panelRound24 = new models.PanelRound();
        varillaImageFour = new javax.swing.JLabel();
        varillaNameFour = new javax.swing.JLabel();
        varillaPriceFour = new javax.swing.JLabel();
        varillaDescriptionFour = new javax.swing.JLabel();
        varillaStoreNameFour = new javax.swing.JLabel();
        varillaAddressStoreFour = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        panelRound25 = new models.PanelRound();
        Image17 = new javax.swing.JLabel();
        gravaNameOne = new javax.swing.JLabel();
        gravaPriceOne = new javax.swing.JLabel();
        gravaDescriptionOne = new javax.swing.JLabel();
        gravaStoreNameOne = new javax.swing.JLabel();
        gravaAddressStoreOne = new javax.swing.JLabel();
        panelRound26 = new models.PanelRound();
        Image18 = new javax.swing.JLabel();
        gravaNameTwo = new javax.swing.JLabel();
        gravaPriceTwo = new javax.swing.JLabel();
        gravaDescriptionTwo = new javax.swing.JLabel();
        gravaStoreNameTwo = new javax.swing.JLabel();
        gravaAddressStoreTwo = new javax.swing.JLabel();
        panelRound27 = new models.PanelRound();
        Image19 = new javax.swing.JLabel();
        gravaNameThree = new javax.swing.JLabel();
        gravaPriceThree = new javax.swing.JLabel();
        gravaDescriptionThree = new javax.swing.JLabel();
        gravaStoreNameThree = new javax.swing.JLabel();
        gravaAddressStoreThree = new javax.swing.JLabel();
        panelRound28 = new models.PanelRound();
        Image20 = new javax.swing.JLabel();
        gravaNameFour = new javax.swing.JLabel();
        priceOne13 = new javax.swing.JLabel();
        gravaDescriptionFour = new javax.swing.JLabel();
        gravaStoreNameFour = new javax.swing.JLabel();
        gravaAddressStoreFour = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        inputNameShow = new javax.swing.JLabel();
        inputTelShow = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        logOut = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();

        panelRound5.setBackground(new java.awt.Color(51, 51, 51));
        panelRound5.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        panelRound5.setRoundBottomLeft(50);
        panelRound5.setRoundBottomRight(50);
        panelRound5.setRoundTopLeft(50);
        panelRound5.setRoundTopRight(50);

        javax.swing.GroupLayout panelRound5Layout = new javax.swing.GroupLayout(panelRound5);
        panelRound5.setLayout(panelRound5Layout);
        panelRound5Layout.setHorizontalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );
        panelRound5Layout.setVerticalGroup(
            panelRound5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Concreto");

        panelRound17.setBackground(new java.awt.Color(51, 51, 51));
        panelRound17.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panelRound17Layout = new javax.swing.GroupLayout(panelRound17);
        panelRound17.setLayout(panelRound17Layout);
        panelRound17Layout.setHorizontalGroup(
            panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        panelRound17Layout.setVerticalGroup(
            panelRound17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelRound18.setBackground(new java.awt.Color(51, 51, 51));
        panelRound18.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panelRound18Layout = new javax.swing.GroupLayout(panelRound18);
        panelRound18.setLayout(panelRound18Layout);
        panelRound18Layout.setHorizontalGroup(
            panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        panelRound18Layout.setVerticalGroup(
            panelRound18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panelRound19.setBackground(new java.awt.Color(51, 51, 51));
        panelRound19.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panelRound19Layout = new javax.swing.GroupLayout(panelRound19);
        panelRound19.setLayout(panelRound19Layout);
        panelRound19Layout.setHorizontalGroup(
            panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 290, Short.MAX_VALUE)
        );
        panelRound19Layout.setVerticalGroup(
            panelRound19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 242, Short.MAX_VALUE)
        );

        panelRound20.setBackground(new java.awt.Color(51, 51, 51));
        panelRound20.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout panelRound20Layout = new javax.swing.GroupLayout(panelRound20);
        panelRound20.setLayout(panelRound20Layout);
        panelRound20Layout.setHorizontalGroup(
            panelRound20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        panelRound20Layout.setVerticalGroup(
            panelRound20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRound1.setBackground(new java.awt.Color(0, 0, 0));
        panelRound1.setForeground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Arena");

        panelRound4.setBackground(new java.awt.Color(51, 51, 51, 70));

        arenaImageOne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        arenaImageOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        arenaNameOne.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        arenaNameOne.setForeground(new java.awt.Color(255, 204, 0));
        arenaNameOne.setText("Litle Ceaser");

        arenaDescriptionOne.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        arenaDescriptionOne.setForeground(new java.awt.Color(255, 255, 255));
        arenaDescriptionOne.setText("jLabel3");

        storeNameOne.setForeground(new java.awt.Color(255, 0, 0));
        storeNameOne.setText("jLabel3");

        arenaPriceOne.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        arenaPriceOne.setForeground(new java.awt.Color(0, 255, 0));
        arenaPriceOne.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        arenaPriceOne.setText("+");

        addressOne.setForeground(new java.awt.Color(255, 255, 255));
        addressOne.setText("jLabel3");

        javax.swing.GroupLayout panelRound4Layout = new javax.swing.GroupLayout(panelRound4);
        panelRound4.setLayout(panelRound4Layout);
        panelRound4Layout.setHorizontalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(arenaImageOne, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(arenaDescriptionOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(storeNameOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound4Layout.createSequentialGroup()
                        .addComponent(arenaNameOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(96, 96, 96))
                    .addComponent(addressOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound4Layout.createSequentialGroup()
                        .addComponent(arenaPriceOne, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelRound4Layout.setVerticalGroup(
            panelRound4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound4Layout.createSequentialGroup()
                .addComponent(arenaImageOne, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(arenaNameOne, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(arenaDescriptionOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(storeNameOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addressOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(arenaPriceOne)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelRound6.setBackground(new java.awt.Color(51, 51, 51, 0));
        panelRound6.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        arenaImageTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_2.gif"))); // NOI18N

        arenaNameTwo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        arenaNameTwo.setForeground(new java.awt.Color(255, 204, 0));
        arenaNameTwo.setText("jLabel3");

        arenaDescriptionTwo.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        arenaDescriptionTwo.setForeground(new java.awt.Color(255, 255, 255));
        arenaDescriptionTwo.setText("jLabel3");

        arenaPriceTwo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        arenaPriceTwo.setForeground(new java.awt.Color(0, 255, 0));
        arenaPriceTwo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        arenaPriceTwo.setText("+");

        storeNameTwo.setForeground(new java.awt.Color(255, 255, 255));
        storeNameTwo.setText("jLabel3");

        addressTwo.setForeground(new java.awt.Color(255, 255, 255));
        addressTwo.setText("jLabel3");

        javax.swing.GroupLayout panelRound6Layout = new javax.swing.GroupLayout(panelRound6);
        panelRound6.setLayout(panelRound6Layout);
        panelRound6Layout.setHorizontalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(arenaImageTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound6Layout.createSequentialGroup()
                        .addComponent(arenaNameTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arenaPriceTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(arenaDescriptionTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(storeNameTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addressTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound6Layout.setVerticalGroup(
            panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound6Layout.createSequentialGroup()
                .addComponent(arenaImageTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arenaNameTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arenaPriceTwo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(arenaDescriptionTwo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(storeNameTwo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addressTwo))
        );

        panelRound7.setBackground(new java.awt.Color(51, 51, 51, 0));
        panelRound7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        arenaImageThree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_3.gif"))); // NOI18N

        arenaNameThree.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        arenaNameThree.setForeground(new java.awt.Color(255, 204, 0));
        arenaNameThree.setText("jLabel3");

        arenaDescriptionThree.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        arenaDescriptionThree.setForeground(new java.awt.Color(255, 255, 255));
        arenaDescriptionThree.setText("jLabel3");

        arenaPriceThree.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        arenaPriceThree.setForeground(new java.awt.Color(0, 255, 0));
        arenaPriceThree.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        arenaPriceThree.setText("+");

        storeNameThree.setForeground(new java.awt.Color(255, 255, 255));
        storeNameThree.setText("jLabel3");

        addressThree.setForeground(new java.awt.Color(255, 255, 255));
        addressThree.setText("jLabel3");

        javax.swing.GroupLayout panelRound7Layout = new javax.swing.GroupLayout(panelRound7);
        panelRound7.setLayout(panelRound7Layout);
        panelRound7Layout.setHorizontalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(arenaImageThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound7Layout.createSequentialGroup()
                        .addComponent(arenaNameThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arenaPriceThree, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(arenaDescriptionThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(storeNameThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addressThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound7Layout.setVerticalGroup(
            panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(arenaImageThree, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arenaNameThree, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arenaPriceThree))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(arenaDescriptionThree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(storeNameThree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addressThree))
        );

        panelRound8.setBackground(new java.awt.Color(51, 51, 51, 0));
        panelRound8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        arenaImageFour.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_4.gif"))); // NOI18N

        arenaNameFour.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        arenaNameFour.setForeground(new java.awt.Color(255, 204, 0));
        arenaNameFour.setText("jLabel3");

        arenaDescriptionFour.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        arenaDescriptionFour.setForeground(new java.awt.Color(255, 255, 255));
        arenaDescriptionFour.setText("jLabel3");

        arenaPriceFour.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        arenaPriceFour.setForeground(new java.awt.Color(0, 255, 0));
        arenaPriceFour.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        arenaPriceFour.setText("+");

        storeNameFour.setForeground(new java.awt.Color(255, 255, 255));
        storeNameFour.setText("jLabel3");

        addressFour.setForeground(new java.awt.Color(255, 255, 255));
        addressFour.setText("jLabel3");

        javax.swing.GroupLayout panelRound8Layout = new javax.swing.GroupLayout(panelRound8);
        panelRound8.setLayout(panelRound8Layout);
        panelRound8Layout.setHorizontalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(arenaImageFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound8Layout.createSequentialGroup()
                        .addComponent(arenaNameFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(arenaPriceFour, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(arenaDescriptionFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(storeNameFour, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addressFour, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound8Layout.setVerticalGroup(
            panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(arenaImageFour, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(arenaNameFour, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(arenaPriceFour))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(arenaDescriptionFour)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(storeNameFour)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addressFour))
        );

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Cemento");

        panelRound9.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound9.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cementoImageOne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cementoImageOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        cementoNameOne.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cementoNameOne.setForeground(new java.awt.Color(255, 204, 0));
        cementoNameOne.setText("Litle Ceaser");

        cementoPriceOne.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cementoPriceOne.setForeground(new java.awt.Color(0, 255, 0));
        cementoPriceOne.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cementoPriceOne.setText("+");

        cementoDescriptionOne.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        cementoDescriptionOne.setForeground(new java.awt.Color(255, 255, 255));
        cementoDescriptionOne.setText("jLabel3");

        cementoStoreNameOne.setForeground(new java.awt.Color(255, 255, 255));
        cementoStoreNameOne.setText("jLabel3");

        cementoAddressStoreOne.setForeground(new java.awt.Color(255, 255, 255));
        cementoAddressStoreOne.setText("jLabel3");

        javax.swing.GroupLayout panelRound9Layout = new javax.swing.GroupLayout(panelRound9);
        panelRound9.setLayout(panelRound9Layout);
        panelRound9Layout.setHorizontalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cementoImageOne, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cementoDescriptionOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cementoStoreNameOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound9Layout.createSequentialGroup()
                        .addComponent(cementoNameOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cementoPriceOne, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cementoAddressStoreOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound9Layout.setVerticalGroup(
            panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound9Layout.createSequentialGroup()
                .addComponent(cementoImageOne, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cementoNameOne, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cementoPriceOne))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cementoDescriptionOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cementoStoreNameOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cementoAddressStoreOne))
        );

        panelRound10.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound10.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cementoImageTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_2.gif"))); // NOI18N

        cementoNameTwo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cementoNameTwo.setForeground(new java.awt.Color(255, 204, 0));
        cementoNameTwo.setText("jLabel3");

        cementoPriceTwo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cementoPriceTwo.setForeground(new java.awt.Color(0, 255, 0));
        cementoPriceTwo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cementoPriceTwo.setText("+");

        cementoDescriptionTwo.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        cementoDescriptionTwo.setForeground(new java.awt.Color(255, 255, 255));
        cementoDescriptionTwo.setText("jLabel3");

        cementoStoreNameTwo.setForeground(new java.awt.Color(255, 255, 255));
        cementoStoreNameTwo.setText("jLabel3");

        cementoAddressStoreTwo.setForeground(new java.awt.Color(255, 255, 255));
        cementoAddressStoreTwo.setText("jLabel3");

        javax.swing.GroupLayout panelRound10Layout = new javax.swing.GroupLayout(panelRound10);
        panelRound10.setLayout(panelRound10Layout);
        panelRound10Layout.setHorizontalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cementoImageTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound10Layout.createSequentialGroup()
                        .addComponent(cementoNameTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cementoPriceTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cementoDescriptionTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cementoStoreNameTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cementoAddressStoreTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound10Layout.setVerticalGroup(
            panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound10Layout.createSequentialGroup()
                .addComponent(cementoImageTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cementoNameTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cementoPriceTwo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cementoDescriptionTwo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cementoStoreNameTwo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cementoAddressStoreTwo))
        );

        panelRound11.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound11.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cementoImageThree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_3.gif"))); // NOI18N

        cementoNameThree.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cementoNameThree.setForeground(new java.awt.Color(255, 204, 0));
        cementoNameThree.setText("jLabel3");

        cementoPriceThree.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cementoPriceThree.setForeground(new java.awt.Color(0, 255, 0));
        cementoPriceThree.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cementoPriceThree.setText("+");

        cementoDescriptionThree.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        cementoDescriptionThree.setForeground(new java.awt.Color(255, 255, 255));
        cementoDescriptionThree.setText("jLabel3");

        cementoStoreNameThree.setForeground(new java.awt.Color(255, 255, 255));
        cementoStoreNameThree.setText("jLabel3");

        cementoAddressStoreThree.setForeground(new java.awt.Color(255, 255, 255));
        cementoAddressStoreThree.setText("jLabel3");

        javax.swing.GroupLayout panelRound11Layout = new javax.swing.GroupLayout(panelRound11);
        panelRound11.setLayout(panelRound11Layout);
        panelRound11Layout.setHorizontalGroup(
            panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cementoImageThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound11Layout.createSequentialGroup()
                        .addComponent(cementoNameThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cementoPriceThree, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cementoDescriptionThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cementoStoreNameThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cementoAddressStoreThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound11Layout.setVerticalGroup(
            panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cementoImageThree, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cementoNameThree, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cementoPriceThree))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cementoDescriptionThree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cementoStoreNameThree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cementoAddressStoreThree))
        );

        panelRound12.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound12.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        cementoImageFour.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_4.gif"))); // NOI18N

        cementoNameFour.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cementoNameFour.setForeground(new java.awt.Color(255, 204, 0));
        cementoNameFour.setText("jLabel3");

        cementoPriceFour.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        cementoPriceFour.setForeground(new java.awt.Color(0, 255, 0));
        cementoPriceFour.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        cementoPriceFour.setText("+");

        cementoDescriptionFour.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        cementoDescriptionFour.setForeground(new java.awt.Color(255, 255, 255));
        cementoDescriptionFour.setText("jLabel3");

        cementoStoreNameFour.setForeground(new java.awt.Color(255, 255, 255));
        cementoStoreNameFour.setText("jLabel3");

        cementoAddressStoreFour.setForeground(new java.awt.Color(255, 255, 255));
        cementoAddressStoreFour.setText("jLabel3");

        javax.swing.GroupLayout panelRound12Layout = new javax.swing.GroupLayout(panelRound12);
        panelRound12.setLayout(panelRound12Layout);
        panelRound12Layout.setHorizontalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cementoImageFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound12Layout.createSequentialGroup()
                        .addComponent(cementoNameFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cementoPriceFour, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cementoDescriptionFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cementoStoreNameFour, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cementoAddressStoreFour, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound12Layout.setVerticalGroup(
            panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cementoImageFour, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cementoNameFour, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cementoPriceFour))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cementoDescriptionFour)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cementoStoreNameFour)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cementoAddressStoreFour))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Concreto");

        panelRound13.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound13.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        concretoImageOne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        concretoImageOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        concretoNameOne.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        concretoNameOne.setForeground(new java.awt.Color(255, 204, 0));
        concretoNameOne.setText("Litle Ceaser");

        concretoPriceOne.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        concretoPriceOne.setForeground(new java.awt.Color(0, 255, 0));
        concretoPriceOne.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        concretoPriceOne.setText("+");

        concretoDescriptionOne.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        concretoDescriptionOne.setForeground(new java.awt.Color(255, 255, 255));
        concretoDescriptionOne.setText("jLabel3");

        concretoStoreNameOne.setForeground(new java.awt.Color(255, 255, 255));
        concretoStoreNameOne.setText("jLabel3");

        concretoAddressStoreOne.setForeground(new java.awt.Color(255, 255, 255));
        concretoAddressStoreOne.setText("jLabel3");

        javax.swing.GroupLayout panelRound13Layout = new javax.swing.GroupLayout(panelRound13);
        panelRound13.setLayout(panelRound13Layout);
        panelRound13Layout.setHorizontalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(concretoImageOne, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(concretoDescriptionOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(concretoStoreNameOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound13Layout.createSequentialGroup()
                        .addComponent(concretoNameOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(concretoPriceOne, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(concretoAddressStoreOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound13Layout.setVerticalGroup(
            panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound13Layout.createSequentialGroup()
                .addComponent(concretoImageOne, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(concretoNameOne, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(concretoPriceOne))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(concretoDescriptionOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(concretoStoreNameOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(concretoAddressStoreOne))
        );

        panelRound14.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound14.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        concretoImageTwo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        concretoImageTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        concretoNameTwo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        concretoNameTwo.setForeground(new java.awt.Color(255, 204, 0));
        concretoNameTwo.setText("Litle Ceaser");

        concretoPriceTwo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        concretoPriceTwo.setForeground(new java.awt.Color(0, 255, 0));
        concretoPriceTwo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        concretoPriceTwo.setText("+");

        concretoDescriptionTwo.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        concretoDescriptionTwo.setForeground(new java.awt.Color(255, 255, 255));
        concretoDescriptionTwo.setText("jLabel3");

        concretoStoreNameTwo.setForeground(new java.awt.Color(255, 255, 255));
        concretoStoreNameTwo.setText("jLabel3");

        concretoAddressStoreTwo.setForeground(new java.awt.Color(255, 255, 255));
        concretoAddressStoreTwo.setText("jLabel3");

        javax.swing.GroupLayout panelRound14Layout = new javax.swing.GroupLayout(panelRound14);
        panelRound14.setLayout(panelRound14Layout);
        panelRound14Layout.setHorizontalGroup(
            panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(concretoImageTwo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(concretoDescriptionTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(concretoStoreNameTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound14Layout.createSequentialGroup()
                        .addComponent(concretoNameTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(concretoPriceTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(concretoAddressStoreTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound14Layout.setVerticalGroup(
            panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound14Layout.createSequentialGroup()
                .addComponent(concretoImageTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(concretoNameTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(concretoPriceTwo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(concretoDescriptionTwo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(concretoStoreNameTwo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(concretoAddressStoreTwo))
        );

        panelRound15.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound15.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        concretoImageThree.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        concretoImageThree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        concretoNameThree.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        concretoNameThree.setForeground(new java.awt.Color(255, 204, 0));
        concretoNameThree.setText("Litle Ceaser");

        concretoPriceThree.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        concretoPriceThree.setForeground(new java.awt.Color(0, 255, 0));
        concretoPriceThree.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        concretoPriceThree.setText("+");

        concretoDescriptionThree.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        concretoDescriptionThree.setForeground(new java.awt.Color(255, 255, 255));
        concretoDescriptionThree.setText("jLabel3");

        concretoStoreNameThree.setForeground(new java.awt.Color(255, 255, 255));
        concretoStoreNameThree.setText("jLabel3");

        concretoAddressStoreThree.setForeground(new java.awt.Color(255, 255, 255));
        concretoAddressStoreThree.setText("jLabel3");

        javax.swing.GroupLayout panelRound15Layout = new javax.swing.GroupLayout(panelRound15);
        panelRound15.setLayout(panelRound15Layout);
        panelRound15Layout.setHorizontalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(concretoImageThree, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(concretoDescriptionThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(concretoStoreNameThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound15Layout.createSequentialGroup()
                        .addComponent(concretoNameThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(concretoPriceThree, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(concretoAddressStoreThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound15Layout.setVerticalGroup(
            panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound15Layout.createSequentialGroup()
                .addComponent(concretoImageThree, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(concretoNameThree, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(concretoPriceThree))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(concretoDescriptionThree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(concretoStoreNameThree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(concretoAddressStoreThree))
        );

        panelRound16.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound16.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        concretoImageFour.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        concretoImageFour.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        concretoNameFour.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        concretoNameFour.setForeground(new java.awt.Color(255, 204, 0));
        concretoNameFour.setText("Litle Ceaser");

        concretoPriceFour.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        concretoPriceFour.setForeground(new java.awt.Color(0, 255, 0));
        concretoPriceFour.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        concretoPriceFour.setText("+");

        concretoDescriptionFour.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        concretoDescriptionFour.setForeground(new java.awt.Color(255, 255, 255));
        concretoDescriptionFour.setText("jLabel3");

        concretoStoreNameFour.setForeground(new java.awt.Color(255, 255, 255));
        concretoStoreNameFour.setText("jLabel3");

        concretoAddressStoreFour.setForeground(new java.awt.Color(255, 255, 255));
        concretoAddressStoreFour.setText("jLabel3");

        javax.swing.GroupLayout panelRound16Layout = new javax.swing.GroupLayout(panelRound16);
        panelRound16.setLayout(panelRound16Layout);
        panelRound16Layout.setHorizontalGroup(
            panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(concretoImageFour, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(concretoDescriptionFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(concretoStoreNameFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound16Layout.createSequentialGroup()
                        .addComponent(concretoNameFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(concretoPriceFour, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(concretoAddressStoreFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound16Layout.setVerticalGroup(
            panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound16Layout.createSequentialGroup()
                .addComponent(concretoImageFour, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(concretoNameFour, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(concretoPriceFour))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(concretoDescriptionFour)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(concretoStoreNameFour)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(concretoAddressStoreFour))
        );

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Varilla");

        panelRound21.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound21.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        varillaImageOne.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        varillaImageOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        varillaNameOne.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        varillaNameOne.setForeground(new java.awt.Color(255, 204, 0));
        varillaNameOne.setText("Litle Ceaser");

        varillaPriceOne.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        varillaPriceOne.setForeground(new java.awt.Color(0, 255, 0));
        varillaPriceOne.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        varillaPriceOne.setText("+");

        varillaDescriptionOne.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        varillaDescriptionOne.setForeground(new java.awt.Color(255, 255, 255));
        varillaDescriptionOne.setText("jLabel3");

        varillaStoreNameOne.setForeground(new java.awt.Color(255, 255, 255));
        varillaStoreNameOne.setText("jLabel3");

        varillaAddressStoreOne.setForeground(new java.awt.Color(255, 255, 255));
        varillaAddressStoreOne.setText("jLabel3");

        javax.swing.GroupLayout panelRound21Layout = new javax.swing.GroupLayout(panelRound21);
        panelRound21.setLayout(panelRound21Layout);
        panelRound21Layout.setHorizontalGroup(
            panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(varillaImageOne, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound21Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(varillaDescriptionOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(varillaStoreNameOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound21Layout.createSequentialGroup()
                        .addComponent(varillaNameOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(varillaPriceOne, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(varillaAddressStoreOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound21Layout.setVerticalGroup(
            panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound21Layout.createSequentialGroup()
                .addComponent(varillaImageOne, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(varillaNameOne, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(varillaPriceOne))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(varillaDescriptionOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(varillaStoreNameOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(varillaAddressStoreOne))
        );

        panelRound22.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound22.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        varillaImageTwo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        varillaImageTwo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        varillaNameTwo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        varillaNameTwo.setForeground(new java.awt.Color(255, 204, 0));
        varillaNameTwo.setText("Litle Ceaser");

        varillaPriceTwo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        varillaPriceTwo.setForeground(new java.awt.Color(0, 255, 0));
        varillaPriceTwo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        varillaPriceTwo.setText("+");

        varillaDescriptionTwo.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        varillaDescriptionTwo.setForeground(new java.awt.Color(255, 255, 255));
        varillaDescriptionTwo.setText("jLabel3");

        varillaStoreNameTwo.setForeground(new java.awt.Color(255, 255, 255));
        varillaStoreNameTwo.setText("jLabel3");

        varillaAddressStoreTwo.setForeground(new java.awt.Color(255, 255, 255));
        varillaAddressStoreTwo.setText("jLabel3");

        javax.swing.GroupLayout panelRound22Layout = new javax.swing.GroupLayout(panelRound22);
        panelRound22.setLayout(panelRound22Layout);
        panelRound22Layout.setHorizontalGroup(
            panelRound22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(varillaImageTwo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(varillaDescriptionTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(varillaStoreNameTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound22Layout.createSequentialGroup()
                        .addComponent(varillaNameTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(varillaPriceTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(varillaAddressStoreTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound22Layout.setVerticalGroup(
            panelRound22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound22Layout.createSequentialGroup()
                .addComponent(varillaImageTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(varillaNameTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(varillaPriceTwo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(varillaDescriptionTwo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(varillaStoreNameTwo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(varillaAddressStoreTwo))
        );

        panelRound23.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound23.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        varillaImageThree.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        varillaImageThree.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        varillaNameThree.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        varillaNameThree.setForeground(new java.awt.Color(255, 204, 0));
        varillaNameThree.setText("Litle Ceaser");

        varillaPriceThree.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        varillaPriceThree.setForeground(new java.awt.Color(0, 255, 0));
        varillaPriceThree.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        varillaPriceThree.setText("+");

        varillaDescriptionThree.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        varillaDescriptionThree.setForeground(new java.awt.Color(255, 255, 255));
        varillaDescriptionThree.setText("jLabel3");

        varillaStoreNameThree.setForeground(new java.awt.Color(255, 255, 255));
        varillaStoreNameThree.setText("jLabel3");

        varillaAddressStoreThree.setForeground(new java.awt.Color(255, 255, 255));
        varillaAddressStoreThree.setText("jLabel3");

        javax.swing.GroupLayout panelRound23Layout = new javax.swing.GroupLayout(panelRound23);
        panelRound23.setLayout(panelRound23Layout);
        panelRound23Layout.setHorizontalGroup(
            panelRound23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(varillaImageThree, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(varillaDescriptionThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(varillaStoreNameThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound23Layout.createSequentialGroup()
                        .addComponent(varillaNameThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(varillaPriceThree, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(varillaAddressStoreThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound23Layout.setVerticalGroup(
            panelRound23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound23Layout.createSequentialGroup()
                .addComponent(varillaImageThree, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(varillaNameThree, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(varillaPriceThree))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(varillaDescriptionThree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(varillaStoreNameThree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(varillaAddressStoreThree))
        );

        panelRound24.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound24.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        varillaImageFour.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        varillaImageFour.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        varillaNameFour.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        varillaNameFour.setForeground(new java.awt.Color(255, 204, 0));
        varillaNameFour.setText("Litle Ceaser");

        varillaPriceFour.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        varillaPriceFour.setForeground(new java.awt.Color(0, 255, 0));
        varillaPriceFour.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        varillaPriceFour.setText("+");

        varillaDescriptionFour.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        varillaDescriptionFour.setForeground(new java.awt.Color(255, 255, 255));
        varillaDescriptionFour.setText("jLabel3");

        varillaStoreNameFour.setForeground(new java.awt.Color(255, 255, 255));
        varillaStoreNameFour.setText("jLabel3");

        varillaAddressStoreFour.setForeground(new java.awt.Color(255, 255, 255));
        varillaAddressStoreFour.setText("jLabel3");

        javax.swing.GroupLayout panelRound24Layout = new javax.swing.GroupLayout(panelRound24);
        panelRound24.setLayout(panelRound24Layout);
        panelRound24Layout.setHorizontalGroup(
            panelRound24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(varillaImageFour, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(varillaDescriptionFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(varillaStoreNameFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound24Layout.createSequentialGroup()
                        .addComponent(varillaNameFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(varillaPriceFour, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(varillaAddressStoreFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound24Layout.setVerticalGroup(
            panelRound24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound24Layout.createSequentialGroup()
                .addComponent(varillaImageFour, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(varillaNameFour, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(varillaPriceFour))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(varillaDescriptionFour)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(varillaStoreNameFour)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(varillaAddressStoreFour))
        );

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 20)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Grava");

        panelRound25.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound25.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Image17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        gravaNameOne.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        gravaNameOne.setForeground(new java.awt.Color(255, 204, 0));
        gravaNameOne.setText("Litle Ceaser");

        gravaPriceOne.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        gravaPriceOne.setForeground(new java.awt.Color(0, 255, 0));
        gravaPriceOne.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gravaPriceOne.setText("+");

        gravaDescriptionOne.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        gravaDescriptionOne.setForeground(new java.awt.Color(255, 255, 255));
        gravaDescriptionOne.setText("jLabel3");

        gravaStoreNameOne.setForeground(new java.awt.Color(255, 255, 255));
        gravaStoreNameOne.setText("jLabel3");

        gravaAddressStoreOne.setForeground(new java.awt.Color(255, 255, 255));
        gravaAddressStoreOne.setText("jLabel3");

        javax.swing.GroupLayout panelRound25Layout = new javax.swing.GroupLayout(panelRound25);
        panelRound25.setLayout(panelRound25Layout);
        panelRound25Layout.setHorizontalGroup(
            panelRound25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gravaDescriptionOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gravaStoreNameOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound25Layout.createSequentialGroup()
                        .addComponent(gravaNameOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gravaPriceOne, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gravaAddressStoreOne, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound25Layout.setVerticalGroup(
            panelRound25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound25Layout.createSequentialGroup()
                .addComponent(Image17, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gravaNameOne, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gravaPriceOne))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gravaDescriptionOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gravaStoreNameOne)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gravaAddressStoreOne))
        );

        panelRound26.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound26.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Image18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        gravaNameTwo.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        gravaNameTwo.setForeground(new java.awt.Color(255, 204, 0));
        gravaNameTwo.setText("Litle Ceaser");

        gravaPriceTwo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        gravaPriceTwo.setForeground(new java.awt.Color(0, 255, 0));
        gravaPriceTwo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gravaPriceTwo.setText("+");

        gravaDescriptionTwo.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        gravaDescriptionTwo.setForeground(new java.awt.Color(255, 255, 255));
        gravaDescriptionTwo.setText("jLabel3");

        gravaStoreNameTwo.setForeground(new java.awt.Color(255, 255, 255));
        gravaStoreNameTwo.setText("jLabel3");

        gravaAddressStoreTwo.setForeground(new java.awt.Color(255, 255, 255));
        gravaAddressStoreTwo.setText("jLabel3");

        javax.swing.GroupLayout panelRound26Layout = new javax.swing.GroupLayout(panelRound26);
        panelRound26.setLayout(panelRound26Layout);
        panelRound26Layout.setHorizontalGroup(
            panelRound26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound26Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gravaDescriptionTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gravaStoreNameTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound26Layout.createSequentialGroup()
                        .addComponent(gravaNameTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gravaPriceTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gravaAddressStoreTwo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound26Layout.setVerticalGroup(
            panelRound26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound26Layout.createSequentialGroup()
                .addComponent(Image18, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gravaNameTwo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gravaPriceTwo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gravaDescriptionTwo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gravaStoreNameTwo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gravaAddressStoreTwo))
        );

        panelRound27.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound27.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Image19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        gravaNameThree.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        gravaNameThree.setForeground(new java.awt.Color(255, 204, 0));
        gravaNameThree.setText("Litle Ceaser");

        gravaPriceThree.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        gravaPriceThree.setForeground(new java.awt.Color(0, 255, 0));
        gravaPriceThree.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        gravaPriceThree.setText("+");

        gravaDescriptionThree.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        gravaDescriptionThree.setForeground(new java.awt.Color(255, 255, 255));
        gravaDescriptionThree.setText("jLabel3");

        gravaStoreNameThree.setForeground(new java.awt.Color(255, 255, 255));
        gravaStoreNameThree.setText("jLabel3");

        gravaAddressStoreThree.setForeground(new java.awt.Color(255, 255, 255));
        gravaAddressStoreThree.setText("jLabel3");

        javax.swing.GroupLayout panelRound27Layout = new javax.swing.GroupLayout(panelRound27);
        panelRound27.setLayout(panelRound27Layout);
        panelRound27Layout.setHorizontalGroup(
            panelRound27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image19, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound27Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gravaDescriptionThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gravaStoreNameThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound27Layout.createSequentialGroup()
                        .addComponent(gravaNameThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gravaPriceThree, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gravaAddressStoreThree, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound27Layout.setVerticalGroup(
            panelRound27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound27Layout.createSequentialGroup()
                .addComponent(Image19, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gravaNameThree, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gravaPriceThree))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gravaDescriptionThree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gravaStoreNameThree)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gravaAddressStoreThree))
        );

        panelRound28.setBackground(new java.awt.Color(51, 51, 51,0));
        panelRound28.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        Image20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Image20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/arena_1.gif"))); // NOI18N

        gravaNameFour.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        gravaNameFour.setForeground(new java.awt.Color(255, 204, 0));
        gravaNameFour.setText("Litle Ceaser");

        priceOne13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        priceOne13.setForeground(new java.awt.Color(0, 255, 0));
        priceOne13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        priceOne13.setText("+");

        gravaDescriptionFour.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        gravaDescriptionFour.setForeground(new java.awt.Color(255, 255, 255));
        gravaDescriptionFour.setText("jLabel3");

        gravaStoreNameFour.setForeground(new java.awt.Color(255, 255, 255));
        gravaStoreNameFour.setText("jLabel3");

        gravaAddressStoreFour.setForeground(new java.awt.Color(255, 255, 255));
        gravaAddressStoreFour.setText("jLabel3");

        javax.swing.GroupLayout panelRound28Layout = new javax.swing.GroupLayout(panelRound28);
        panelRound28.setLayout(panelRound28Layout);
        panelRound28Layout.setHorizontalGroup(
            panelRound28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Image20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
            .addGroup(panelRound28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRound28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gravaDescriptionFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(gravaStoreNameFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelRound28Layout.createSequentialGroup()
                        .addComponent(gravaNameFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(priceOne13, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(gravaAddressStoreFour, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelRound28Layout.setVerticalGroup(
            panelRound28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound28Layout.createSequentialGroup()
                .addComponent(Image20, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelRound28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(gravaNameFour, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceOne13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gravaDescriptionFour)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gravaStoreNameFour)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(gravaAddressStoreFour))
        );

        javax.swing.GroupLayout panelRound1Layout = new javax.swing.GroupLayout(panelRound1);
        panelRound1.setLayout(panelRound1Layout);
        panelRound1Layout.setHorizontalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addComponent(panelRound13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelRound14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelRound15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelRound16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addComponent(panelRound25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelRound26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelRound27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelRound28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26))))
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelRound1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jSeparator6, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(panelRound1Layout.createSequentialGroup()
                        .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(panelRound9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelRound10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelRound11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelRound12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(panelRound4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelRound6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelRound7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelRound8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel5))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(panelRound21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelRound22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelRound23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelRound24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel7))
                            .addGroup(panelRound1Layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel9)))
                        .addGap(0, 22, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelRound1Layout.setVerticalGroup(
            panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRound1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(panelRound9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelRound12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(24, 24, 24)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelRound1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelRound28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelRound27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(panelRound1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 1350, 690));

        inputNameShow.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        inputNameShow.setForeground(new java.awt.Color(255, 255, 255));
        inputNameShow.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        inputNameShow.setText("Nombre");
        jPanel1.add(inputNameShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 90, 160, -1));

        inputTelShow.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        inputTelShow.setForeground(new java.awt.Color(255, 255, 255));
        inputTelShow.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        inputTelShow.setText("Nombre");
        jPanel1.add(inputTelShow, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 110, 160, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Carrito");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jLabel2KeyPressed(evt);
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 30, 90, 30));

        logOut.setFont(new java.awt.Font("Segoe UI", 2, 16)); // NOI18N
        logOut.setForeground(new java.awt.Color(255, 255, 255));
        logOut.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        logOut.setText("Cerrar sesión");
        logOut.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(logOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 30, 130, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Buscar");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 26, -1, 30));

        jLabel1.setBackground(new java.awt.Color(0, 51, 51));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/barra-de-navegacion.gif"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jTextField1.setBackground(new java.awt.Color(0, 0, 0));
        jTextField1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setText("Buscador");
        jTextField1.setBorder(null);
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, 570, 20));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel13.setText("+");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1510, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jLabel2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2KeyPressed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Image17;
    private javax.swing.JLabel Image18;
    private javax.swing.JLabel Image19;
    private javax.swing.JLabel Image20;
    private javax.swing.JLabel addressFour;
    private javax.swing.JLabel addressOne;
    private javax.swing.JLabel addressThree;
    private javax.swing.JLabel addressTwo;
    private javax.swing.JLabel arenaDescriptionFour;
    private javax.swing.JLabel arenaDescriptionOne;
    private javax.swing.JLabel arenaDescriptionThree;
    private javax.swing.JLabel arenaDescriptionTwo;
    private javax.swing.JLabel arenaImageFour;
    private javax.swing.JLabel arenaImageOne;
    private javax.swing.JLabel arenaImageThree;
    private javax.swing.JLabel arenaImageTwo;
    private javax.swing.JLabel arenaNameFour;
    private javax.swing.JLabel arenaNameOne;
    private javax.swing.JLabel arenaNameThree;
    private javax.swing.JLabel arenaNameTwo;
    private javax.swing.JLabel arenaPriceFour;
    private javax.swing.JLabel arenaPriceOne;
    private javax.swing.JLabel arenaPriceThree;
    private javax.swing.JLabel arenaPriceTwo;
    private javax.swing.JLabel cementoAddressStoreFour;
    private javax.swing.JLabel cementoAddressStoreOne;
    private javax.swing.JLabel cementoAddressStoreThree;
    private javax.swing.JLabel cementoAddressStoreTwo;
    private javax.swing.JLabel cementoDescriptionFour;
    private javax.swing.JLabel cementoDescriptionOne;
    private javax.swing.JLabel cementoDescriptionThree;
    private javax.swing.JLabel cementoDescriptionTwo;
    private javax.swing.JLabel cementoImageFour;
    private javax.swing.JLabel cementoImageOne;
    private javax.swing.JLabel cementoImageThree;
    private javax.swing.JLabel cementoImageTwo;
    private javax.swing.JLabel cementoNameFour;
    private javax.swing.JLabel cementoNameOne;
    private javax.swing.JLabel cementoNameThree;
    private javax.swing.JLabel cementoNameTwo;
    private javax.swing.JLabel cementoPriceFour;
    private javax.swing.JLabel cementoPriceOne;
    private javax.swing.JLabel cementoPriceThree;
    private javax.swing.JLabel cementoPriceTwo;
    private javax.swing.JLabel cementoStoreNameFour;
    private javax.swing.JLabel cementoStoreNameOne;
    private javax.swing.JLabel cementoStoreNameThree;
    private javax.swing.JLabel cementoStoreNameTwo;
    private javax.swing.JLabel concretoAddressStoreFour;
    private javax.swing.JLabel concretoAddressStoreOne;
    private javax.swing.JLabel concretoAddressStoreThree;
    private javax.swing.JLabel concretoAddressStoreTwo;
    private javax.swing.JLabel concretoDescriptionFour;
    private javax.swing.JLabel concretoDescriptionOne;
    private javax.swing.JLabel concretoDescriptionThree;
    private javax.swing.JLabel concretoDescriptionTwo;
    private javax.swing.JLabel concretoImageFour;
    private javax.swing.JLabel concretoImageOne;
    private javax.swing.JLabel concretoImageThree;
    private javax.swing.JLabel concretoImageTwo;
    private javax.swing.JLabel concretoNameFour;
    private javax.swing.JLabel concretoNameOne;
    private javax.swing.JLabel concretoNameThree;
    private javax.swing.JLabel concretoNameTwo;
    private javax.swing.JLabel concretoPriceFour;
    private javax.swing.JLabel concretoPriceOne;
    private javax.swing.JLabel concretoPriceThree;
    private javax.swing.JLabel concretoPriceTwo;
    private javax.swing.JLabel concretoStoreNameFour;
    private javax.swing.JLabel concretoStoreNameOne;
    private javax.swing.JLabel concretoStoreNameThree;
    private javax.swing.JLabel concretoStoreNameTwo;
    private javax.swing.JLabel gravaAddressStoreFour;
    private javax.swing.JLabel gravaAddressStoreOne;
    private javax.swing.JLabel gravaAddressStoreThree;
    private javax.swing.JLabel gravaAddressStoreTwo;
    private javax.swing.JLabel gravaDescriptionFour;
    private javax.swing.JLabel gravaDescriptionOne;
    private javax.swing.JLabel gravaDescriptionThree;
    private javax.swing.JLabel gravaDescriptionTwo;
    private javax.swing.JLabel gravaNameFour;
    private javax.swing.JLabel gravaNameOne;
    private javax.swing.JLabel gravaNameThree;
    private javax.swing.JLabel gravaNameTwo;
    private javax.swing.JLabel gravaPriceOne;
    private javax.swing.JLabel gravaPriceThree;
    private javax.swing.JLabel gravaPriceTwo;
    private javax.swing.JLabel gravaStoreNameFour;
    private javax.swing.JLabel gravaStoreNameOne;
    private javax.swing.JLabel gravaStoreNameThree;
    private javax.swing.JLabel gravaStoreNameTwo;
    private javax.swing.JLabel inputNameShow;
    private javax.swing.JLabel inputTelShow;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel logOut;
    private models.PanelRound panelRound1;
    private models.PanelRound panelRound10;
    private models.PanelRound panelRound11;
    private models.PanelRound panelRound12;
    private models.PanelRound panelRound13;
    private models.PanelRound panelRound14;
    private models.PanelRound panelRound15;
    private models.PanelRound panelRound16;
    private models.PanelRound panelRound17;
    private models.PanelRound panelRound18;
    private models.PanelRound panelRound19;
    private models.PanelRound panelRound20;
    private models.PanelRound panelRound21;
    private models.PanelRound panelRound22;
    private models.PanelRound panelRound23;
    private models.PanelRound panelRound24;
    private models.PanelRound panelRound25;
    private models.PanelRound panelRound26;
    private models.PanelRound panelRound27;
    private models.PanelRound panelRound28;
    private models.PanelRound panelRound4;
    private models.PanelRound panelRound5;
    private models.PanelRound panelRound6;
    private models.PanelRound panelRound7;
    private models.PanelRound panelRound8;
    private models.PanelRound panelRound9;
    private javax.swing.JLabel priceOne13;
    private javax.swing.JLabel storeNameFour;
    private javax.swing.JLabel storeNameOne;
    private javax.swing.JLabel storeNameThree;
    private javax.swing.JLabel storeNameTwo;
    private javax.swing.JLabel varillaAddressStoreFour;
    private javax.swing.JLabel varillaAddressStoreOne;
    private javax.swing.JLabel varillaAddressStoreThree;
    private javax.swing.JLabel varillaAddressStoreTwo;
    private javax.swing.JLabel varillaDescriptionFour;
    private javax.swing.JLabel varillaDescriptionOne;
    private javax.swing.JLabel varillaDescriptionThree;
    private javax.swing.JLabel varillaDescriptionTwo;
    private javax.swing.JLabel varillaImageFour;
    private javax.swing.JLabel varillaImageOne;
    private javax.swing.JLabel varillaImageThree;
    private javax.swing.JLabel varillaImageTwo;
    private javax.swing.JLabel varillaNameFour;
    private javax.swing.JLabel varillaNameOne;
    private javax.swing.JLabel varillaNameThree;
    private javax.swing.JLabel varillaNameTwo;
    private javax.swing.JLabel varillaPriceFour;
    private javax.swing.JLabel varillaPriceOne;
    private javax.swing.JLabel varillaPriceThree;
    private javax.swing.JLabel varillaPriceTwo;
    private javax.swing.JLabel varillaStoreNameFour;
    private javax.swing.JLabel varillaStoreNameOne;
    private javax.swing.JLabel varillaStoreNameThree;
    private javax.swing.JLabel varillaStoreNameTwo;
    // End of variables declaration//GEN-END:variables
}
