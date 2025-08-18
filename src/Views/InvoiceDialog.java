/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import models.Invoice;
import services.CheckoutService;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.io.*;
/**
 *
 * @author Geison Medina
 */
public class InvoiceDialog extends JDialog {
    private final JEditorPane viewer = new JEditorPane();

    public InvoiceDialog(Frame owner, Invoice invoice) {
        super(owner, "Factura " + invoice.getNumber(), true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(760, 640);
        setLocationRelativeTo(owner);

        viewer.setEditable(false);
        viewer.setContentType("text/html");
        viewer.setText(CheckoutService.formatAsHtml(invoice));

        JButton btnPrint = new JButton("Imprimir…");
        JButton btnSave  = new JButton("Guardar como HTML…");
        JButton btnClose = new JButton("Cerrar");

        btnPrint.addActionListener(e -> doPrint());
        btnSave.addActionListener(e -> doSave());
        btnClose.addActionListener(e -> dispose());

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        actions.add(btnPrint);
        actions.add(btnSave);
        actions.add(btnClose);

        getContentPane().setLayout(new BorderLayout(8,8));
        getContentPane().add(new JScrollPane(viewer), BorderLayout.CENTER);
        getContentPane().add(actions, BorderLayout.SOUTH);
    }

    private void doPrint() {
        try {
            viewer.print();
        } catch (PrinterException ex) {
            JOptionPane.showMessageDialog(this, "No se pudo imprimir: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void doSave() {
        JFileChooser fc = new JFileChooser();
        fc.setSelectedFile(new File("factura.html"));
        if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try (Writer w = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fc.getSelectedFile()), "UTF-8"))) {
                w.write(viewer.getText());
                JOptionPane.showMessageDialog(this, "Factura guardada.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "No se pudo guardar: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
