/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import models.ShoppingCart;
import models.CartItem;
import models.Product;
import models.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Ventana de diálogo para mostrar y gestionar una factura digital.
 *
 * <p>
 * La clase {@code InvoiceDialog} genera una representación visual de una
 * factura en formato HTML a partir de los productos almacenados en el
 * {@link ShoppingCart}. Además, permite guardar la factura como archivo HTML o
 * imprimirla/guardarla en PDF.</p>
 *
 * Características principales:
 * <ul>
 * <li>Genera un número de folio único basado en fecha y hora</li>
 * <li>Muestra la factura en un {@link JEditorPane} con estilo HTML/CSS</li>
 * <li>Permite guardar la factura en un archivo HTML</li>
 * <li>Permite enviar la factura a impresión (incluyendo impresión virtual a
 * PDF)</li>
 * </ul>
 *
 * @author
 * @author Geison Medina
 */
public class InvoiceDialog extends JDialog {

    /**
     * Carrito de compras actual.
     */
    private final ShoppingCart cart = ShoppingCart.get();

    /**
     * Usuario asociado a la factura.
     */
    private final Usuario user;

    /**
     * Componente visual para mostrar la factura en HTML.
     */
    private final JEditorPane viewer = new JEditorPane();

    /**
     * Formato numérico para mostrar valores monetarios.
     */
    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    /**
     * Identificador único de la factura (folio).
     */
    private final String folio;

    /**
     * Código HTML de la factura, usado para mostrar y guardar.
     */
    private String html;

    /**
     * Construye un nuevo diálogo de factura.
     *
     * @param owner ventana principal propietaria
     * @param user usuario al que pertenece la factura
     */
    public InvoiceDialog(Frame owner, Usuario user) {
        super(owner, "Factura digital", true);
        this.user = user;
        this.folio = "F-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        setSize(820, 600);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        viewer.setContentType("text/html");
        viewer.setEditable(false);
        buildHtml();
        viewer.setText(html);

        add(new JScrollPane(viewer), BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnSave = new JButton("Guardar como HTML");
        JButton btnPdf = new JButton("Imprimir / Guardar PDF");
        JButton btnClose = new JButton("Cerrar");
        actions.add(btnSave);
        actions.add(btnPdf);
        actions.add(btnClose);
        add(actions, BorderLayout.SOUTH);

        btnSave.addActionListener(e -> saveHtml());
        btnPdf.addActionListener(e -> printToPdf());
        btnClose.addActionListener(e -> dispose());
    }

    private void buildHtml() {
        String fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date());
        StringBuilder rows = new StringBuilder();

        for (CartItem it : cart.getItems()) {
            Product p = it.getProduct();
            rows.append("<tr>")
                    .append("<td>").append(escape(p.getProductName())).append("</td>")
                    .append("<td style='text-align:center;'>").append(escape(p.getUnit())).append("</td>")
                    .append("<td style='text-align:right;'>").append(df.format(it.getQuantity())).append("</td>")
                    .append("<td style='text-align:right;'>RD$ ").append(df.format(p.getPrice())).append("</td>")
                    .append("<td style='text-align:right;'>RD$ ").append(df.format(it.getLineTotal())).append("</td>")
                    .append("</tr>");
        }

        String css = """
            <style>
              body{font-family:Segoe UI,Arial,sans-serif;margin:24px;background:#111;color:#eee;}
              .card{background:#1a1a1a;border-radius:12px;padding:24px;box-shadow:0 6px 24px rgba(0,0,0,.3);}
              h1{margin:0 0 6px 0;color:#ffd166;font-size:22px}
              .muted{color:#bbb;font-size:12px}
              .grid{display:grid;grid-template-columns:1fr 1fr;gap:8px;margin-top:10px}
              table{width:100%;border-collapse:collapse;margin-top:16px}
              th,td{border-bottom:1px solid #2b2b2b;padding:10px 8px}
              th{background:#151515;text-align:left;color:#ddd}
              tfoot td{border:none;padding:6px 8px}
              .right{text-align:right}
              .badge{background:#0033cc;color:#fff;padding:2px 8px;border-radius:999px;font-size:12px}
              .total{font-size:18px;color:#9cff9c}
            </style>
        """;

        String header = """
            <div class='grid'>
              <div>
                <h1>Gestor Constructor</h1>
                <div class='muted'>RNC: — | Av. —, Santo Domingo</div>
                <div class='muted'>Tel: (849) 860-0600</div>
              </div>
              <div class='right'>
                <div class='badge'>Factura digital</div><br/>
                <div class='muted'>%s</div>
                <div class='muted'>Folio: %s</div>
              </div>
            </div>
        """.formatted(fecha, folio);

        String cliente = """
            <div class='grid' style='margin-top:12px'>
              <div>
                <div class='muted'>Cliente</div>
                <div>%s</div>
                <div class='muted'>%s</div>
              </div>
              <div class='right'>
                <div class='muted'>ITBIS</div>
                <div>%s%%</div>
              </div>
            </div>
        """.formatted(escape(user.getFullName()),
                escape("Tel: " + user.getTelefono()),
                df.format(cart.getTaxRate() * 100));

        String totales = """
            <table>
              <thead>
                <tr>
                  <th>Producto</th><th style='text-align:center;'>Unidad</th>
                  <th class='right'>Cant.</th><th class='right'>Precio</th><th class='right'>Importe</th>
                </tr>
              </thead>
              <tbody>%s</tbody>
              <tfoot>
                <tr><td colspan='3'></td><td class='right'>Subtotal</td><td class='right'>RD$ %s</td></tr>
                <tr><td colspan='3'></td><td class='right'>ITBIS</td><td class='right'>RD$ %s</td></tr>
                <tr><td colspan='3'></td><td class='right'><b>Total</b></td><td class='right total'><b>RD$ %s</b></td></tr>
              </tfoot>
            </table>
        """.formatted(rows, df.format(cart.getSubtotal()),
                df.format(cart.getTax()), df.format(cart.getTotal()));

        html = """
            <html><head><meta charset='UTF-8'>%s</head>
            <body><div class='card'>%s%s%s</div></body></html>
        """.formatted(css, header, cliente, totales);
    }

    private void saveHtml() {
        if (cart.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("factura_" + folio + ".html"));
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                Files.write(fc.getSelectedFile().toPath(), html.getBytes(StandardCharsets.UTF_8));
                JOptionPane.showMessageDialog(this, "Guardado: " + fc.getSelectedFile().getAbsolutePath());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "No se pudo guardar: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void printToPdf() {
        try {
            if (cart.getItems().isEmpty()) {
                JOptionPane.showMessageDialog(this, "El carrito está vacío.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            // Abre el cuadro de impresión; el usuario puede elegir "Microsoft Print to PDF"
            viewer.print();
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo imprimir: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
