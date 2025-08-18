/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Views;

import models.CartItem;
import models.Product;
import models.ShoppingCart;
import models.Usuario;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Diálogo del carrito de compras. Muestra ítems, permite editar cantidades,
 * eliminar/vaciar, ver factura digital y realizar la compra (limpiando el
 * carrito).
 */
public class CartDialog extends JDialog {

    private final ShoppingCart cart = ShoppingCart.get();
    private final Usuario user;

    private JTable table;
    private CartTableModel model;
    private final JLabel lblSub = new JLabel();
    private final JLabel lblTax = new JLabel();
    private final JLabel lblTot = new JLabel();
    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    private JButton btnDel;
    private JButton btnClear;
    private JButton btnInvoice;
    private JButton btnClose;
    private JButton btnCheckout;

    public CartDialog(Frame owner, Usuario user) {
        super(owner, "Carrito de compras", true);
        this.user = user;

        buildUI(owner);
        refresh();
    }

    /* ======================= UI ======================= */
    private void buildUI(Frame owner) {
        setSize(780, 520);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout(10, 10));

        // Tabla
        model = new CartTableModel();
        table = new JTable(model);
        table.setRowHeight(26);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Panel inferior: totales + acciones
        JPanel south = new JPanel(new BorderLayout());

        JPanel totals = new JPanel(new GridLayout(3, 2, 8, 4));
        totals.add(new JLabel("Subtotal:"));
        totals.add(lblSub);
        totals.add(new JLabel("ITBIS:"));
        totals.add(lblTax);
        totals.add(new JLabel("Total:"));
        totals.add(lblTot);
        south.add(totals, BorderLayout.EAST);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnDel = new JButton("Eliminar seleccionado");
        btnClear = new JButton("Vaciar carrito");
        btnInvoice = new JButton("Ver factura digital");
        btnClose = new JButton("Cerrar");
        btnCheckout = new JButton("Realizar compra");

        actions.add(btnDel);
        actions.add(btnClear);
        actions.add(btnInvoice);
        actions.add(btnClose);
        actions.add(btnCheckout);
        south.add(actions, BorderLayout.WEST);

        add(south, BorderLayout.SOUTH);

        // Listeners
        btnDel.addActionListener(e -> removeSelected());
        btnClear.addActionListener(e -> {
            cart.clear();
            refresh();
        });
        btnInvoice.addActionListener(e -> showInvoiceSnapshot(cloneItems(cart.getItems()),
                cart.getTaxRate()));
        btnClose.addActionListener(e -> dispose());
        btnCheckout.addActionListener(e -> onCheckout());
    }

    /* ======================= Acciones ======================= */
    private void removeSelected() {
        int row = table.getSelectedRow();
        if (row < 0) {
            return;
        }

        Product p = cart.getItems().get(row).getProduct();
        cart.remove(p);
        refresh();
    }

    private void onCheckout() {
        if (cart.getItems().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío.");
            return;
        }

        int opt = JOptionPane.showConfirmDialog(
                this,
                "Total: RD$ " + df.format(cart.getTotal()) + "\n\n¿Deseas confirmar la compra?",
                "Confirmar compra",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );
        if (opt != JOptionPane.YES_OPTION) {
            return;
        }

        // Snapshot para la factura digital (se usa después de limpiar el carrito)
        List<CartItem> snapshot = cloneItems(cart.getItems());
        double taxRate = cart.getTaxRate();

        // Limpiar carrito y actualizar UI
        cart.clear();
        refresh();

        JOptionPane.showMessageDialog(this,
                "¡Compra realizada con éxito!",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

        // Ofrecer ver la factura digital
        int see = JOptionPane.showOptionDialog(
                this,
                "¿Quieres ver tu factura digital ahora?",
                "Factura generada",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                new Object[]{"Sí, mostrar", "No, gracias"},
                "Sí, mostrar"
        );
        if (see == JOptionPane.YES_OPTION) {
            showInvoiceSnapshot(snapshot, taxRate);
        }

        // Puedes cerrar el diálogo si quieres
        // dispose();
    }

    private List<CartItem> cloneItems(List<CartItem> source) {
        List<CartItem> copy = new ArrayList<>();
        for (CartItem it : source) {
            copy.add(new CartItem(it.getProduct(), it.getQuantity()));
        }
        return copy;
    }

    private void refresh() {
        if (model != null) {
            model.fireTableDataChanged();
        }
        lblSub.setText("RD$ " + df.format(cart.getSubtotal()));
        lblTax.setText("RD$ " + df.format(cart.getTax()));
        lblTot.setText("RD$ " + df.format(cart.getTotal()));

        boolean hasItems = !cart.getItems().isEmpty();
        if (btnDel != null) {
            btnDel.setEnabled(hasItems && table.getSelectedRow() >= 0);
        }
        if (btnClear != null) {
            btnClear.setEnabled(hasItems);
        }
        if (btnInvoice != null) {
            btnInvoice.setEnabled(hasItems);
        }
        if (btnCheckout != null) {
            btnCheckout.setEnabled(hasItems);
        }
    }

    /* ======================= Table Model ======================= */
    private class CartTableModel extends AbstractTableModel {

        private final String[] cols = {"Producto", "Unidad", "Precio", "Cantidad", "Importe"};

        @Override
        public int getRowCount() {
            return cart.getItems().size();
        }

        @Override
        public int getColumnCount() {
            return cols.length;
        }

        @Override
        public String getColumnName(int c) {
            return cols[c];
        }

        @Override
        public boolean isCellEditable(int r, int c) {
            return c == 3;
        } // cantidad

        @Override
        public Object getValueAt(int row, int col) {
            CartItem it = cart.getItems().get(row);
            switch (col) {
                case 0:
                    return it.getProduct().getProductName();
                case 1:
                    return it.getProduct().getUnit();
                case 2:
                    return "RD$ " + new DecimalFormat("#,##0.00").format(it.getProduct().getPrice());
                case 3:
                    return it.getQuantity();
                case 4:
                    return "RD$ " + new DecimalFormat("#,##0.00").format(it.getLineTotal());
                default:
                    return "";
            }
        }

        @Override
        public void setValueAt(Object aValue, int row, int col) {
            if (col != 3) {
                return;
            }
            try {
                double q = Double.parseDouble(aValue.toString());
                if (q <= 0) {
                    return;
                }
                cart.getItems().get(row).setQuantity(q);
                fireTableRowsUpdated(row, row);
                CartDialog.this.refresh();
            } catch (NumberFormatException ignored) {
            }
        }
    }

    /* ======================= Factura digital simple ======================= */
    /**
     * Muestra una factura digital basada en un snapshot de ítems (no usa el
     * carrito actual).
     */
    private void showInvoiceSnapshot(List<CartItem> items, double taxRate) {
        if (items == null || items.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay productos para facturar.");
            return;
        }
        new SimpleInvoiceDialog((Frame) getOwner(), user, items, taxRate).setVisible(true);
    }

    /**
     * Diálogo sencillo para visualizar una factura digital (solo lectura). No
     * depende del estado actual del carrito; usa una copia de los ítems.
     */
    private static class SimpleInvoiceDialog extends JDialog {

        private final DecimalFormat df = new DecimalFormat("#,##0.00");

        SimpleInvoiceDialog(Frame owner, Usuario user, List<CartItem> snapshot, double taxRate) {
            super(owner, "Factura digital", true);
            setSize(720, 520);
            setLocationRelativeTo(owner);
            setLayout(new BorderLayout(10, 10));

            // Cabecera con datos del usuario
            JPanel header = new JPanel(new GridLayout(0, 1));
            header.add(new JLabel("Cliente: " + (user != null ? user.getFullName() : "-")));
            header.add(new JLabel("Teléfono: " + (user != null ? user.getTelefono() : "-")));
            add(header, BorderLayout.NORTH);

            // Tabla de ítems (no editable)
            String[] cols = {"Producto", "Unidad", "Precio", "Cantidad", "Importe"};
            Object[][] data = new Object[snapshot.size()][cols.length];
            double subtotal = 0.0;
            for (int i = 0; i < snapshot.size(); i++) {
                CartItem it = snapshot.get(i);
                double line = it.getLineTotal();
                subtotal += line;
                data[i][0] = it.getProduct().getProductName();
                data[i][1] = it.getProduct().getUnit();
                data[i][2] = "RD$ " + df.format(it.getProduct().getPrice());
                data[i][3] = it.getQuantity();
                data[i][4] = "RD$ " + df.format(line);
            }

            JTable tbl = new JTable(data, cols) {
                @Override
                public boolean isCellEditable(int r, int c) {
                    return false;
                }
            };
            tbl.setRowHeight(26);
            add(new JScrollPane(tbl), BorderLayout.CENTER);

            // Totales
            double tax = subtotal * taxRate;
            double total = subtotal + tax;

            JPanel totals = new JPanel(new GridLayout(3, 2, 8, 4));
            totals.add(new JLabel("Subtotal:"));
            totals.add(new JLabel("RD$ " + df.format(subtotal)));
            totals.add(new JLabel("ITBIS (" + (int) Math.round(taxRate * 100) + "%):"));
            totals.add(new JLabel("RD$ " + df.format(tax)));
            totals.add(new JLabel("Total:"));
            totals.add(new JLabel("RD$ " + df.format(total)));

            JPanel south = new JPanel(new BorderLayout());
            south.add(totals, BorderLayout.EAST);

            JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
            JButton btnClose = new JButton("Cerrar");
            btnClose.addActionListener(e -> dispose());
            actions.add(btnClose);

            south.add(actions, BorderLayout.WEST);
            add(south, BorderLayout.SOUTH);
        }
    }
}
