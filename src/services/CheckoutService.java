/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import models.CartItem;
import models.Invoice;
import models.InvoiceItem;
import models.Product;
import models.ShoppingCart;
import models.Usuario;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Servicio de checkout: genera una factura a partir del carrito y la formatea
 * como HTML para mostrarla o guardarla.
 *
 * <p>
 * Este servicio centraliza la lógica de facturación: - Convierte el carrito en
 * una entidad Invoice. - Genera un número de factura único. - Devuelve la
 * factura formateada en HTML.</p>
 *
 * @author Geison Medina
 */
public class CheckoutService {

    /**
     * Genera la entidad {@link Invoice} a partir del carrito y el usuario.
     *
     * @param cart carrito de compras actual
     * @param user usuario al que se le factura
     * @return factura con los ítems, subtotal, impuestos y totales
     */
    public static Invoice generateInvoice(ShoppingCart cart, Usuario user) {
        List<InvoiceItem> items = new ArrayList<>();

        // Recorremos los ítems del carrito (CartItem) y creamos InvoiceItem
        for (CartItem it : cart.getItems()) {
            Product p = it.getProduct();
            double qty = it.getQuantity();
            double price = p.getPrice(); // el precio unitario lo tomamos de Product
            items.add(new InvoiceItem(p, qty, price));
        }

        // Calculamos subtotal y tax (ITBIS)
        double subtotal = items.stream().mapToDouble(InvoiceItem::getLineTotal).sum();
        double tax = subtotal * cart.getTaxRate();
        String number = makeInvoiceNumber();

        // Creamos la factura con fecha y datos del cliente
        return new Invoice(number, LocalDateTime.now(), user, items, subtotal, tax);
    }

    /**
     * Crea un número de factura único legible. Formato: FAC-YYYYMMDD-HHmmss-XYZ
     *
     * @return número de factura
     */
    private static String makeInvoiceNumber() {
        String ts = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));
        int rnd = new Random().nextInt(900) + 100;
        return "FAC-" + ts + "-" + rnd;
    }

    /**
     * Devuelve la factura en formato HTML. Este HTML se puede usar en un
     * {@code JEditorPane}, enviar por correo o guardar en archivo.
     *
     * @param inv factura a formatear
     * @return representación HTML de la factura
     */
    public static String formatAsHtml(Invoice inv) {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        StringBuilder rows = new StringBuilder();

        // Construimos cada fila de la tabla de productos
        for (InvoiceItem it : inv.getItems()) {
            rows.append("<tr>")
                    .append("<td>").append(escape(it.getProduct().getProductName())).append("</td>")
                    .append("<td style='text-align:right'>").append(df.format(it.getQty()))
                    .append(" ").append(escape(it.getProduct().getUnit())).append("</td>")
                    .append("<td style='text-align:right'>RD$ ").append(df.format(it.getUnitPrice())).append("</td>")
                    .append("<td style='text-align:right'>RD$ ").append(df.format(it.getLineTotal())).append("</td>")
                    .append("</tr>");
        }

        // Plantilla HTML
        String html
                = """
                <html>
                <head>
                  <meta charset='UTF-8'>
                  <style>
                    body { font-family: Arial, sans-serif; margin:16px; color:#111; }
                    h1 { margin:0; }
                    .head { display:flex; justify-content:space-between; align-items:flex-start; }
                    .box { background:#f6f6f6; padding:12px; border-radius:8px; }
                    table { width:100%; border-collapse:collapse; margin-top:14px; }
                    th, td { border-bottom:1px solid #ddd; padding:8px; }
                    th { text-align:left; background:#fafafa; }
                    .totals { margin-top:12px; width:100%; }
                    .totals td { padding:6px 0; }
                    .right { text-align:right; }
                    small{ color:#555; }
                  </style>
                </head>
                <body>
                  <div class='head'>
                    <div>
                      <h1>Factura</h1>
                      <small>""" + escape(inv.getNumber()) + "</small>\n"
                + "    </div>\n"
                + "    <div class='box'>\n"
                + "      <div><b>Cliente:</b> " + escape(inv.getCustomer().getFullName()) + "</div>\n"
                + "      <div><b>Tel:</b> " + escape(inv.getCustomer().getTelefono()) + "</div>\n"
                + "      <div><b>Fecha:</b> " + inv.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "</div>\n"
                + "    </div>\n"
                + "  </div>\n"
                + "\n"
                + "  <table>\n"
                + "    <thead>\n"
                + "      <tr>\n"
                + "        <th>Producto</th>\n"
                + "        <th class='right'>Cantidad</th>\n"
                + "        <th class='right'>Precio</th>\n"
                + "        <th class='right'>Importe</th>\n"
                + "      </tr>\n"
                + "    </thead>\n"
                + "    <tbody>\n"
                + rows
                + "    </tbody>\n"
                + "  </table>\n"
                + "\n"
                + "  <table class='totals'>\n"
                + "    <tr><td class='right'><b>Subtotal:</b></td><td class='right' style='width:140px'>RD$ " + df.format(inv.getSubtotal()) + "</td></tr>\n"
                + "    <tr><td class='right'><b>Impuesto:</b></td><td class='right'>RD$ " + df.format(inv.getTax()) + "</td></tr>\n"
                + "    <tr><td class='right'><b>Total:</b></td><td class='right'><b>RD$ " + df.format(inv.getTotal()) + "</b></td></tr>\n"
                + "  </table>\n"
                + "\n"
                + "  <p><small>Gracias por su compra.</small></p>\n"
                + "</body></html>";

        return html;
    }

    /**
     * Escapa caracteres especiales para evitar romper el HTML.
     *
     * @param s texto original
     * @return texto seguro para HTML
     */
    private static String escape(String s) {
        if (s == null) {
            return "";
        }
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}
