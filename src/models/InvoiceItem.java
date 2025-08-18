/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Geison Medina
 */
public class InvoiceItem {

    /** Producto asociado a la línea de factura. */
    private final Product product;

    /** Cantidad adquirida del producto. */
    private final double qty;

    /** Precio unitario aplicado al producto. */
    private final double unitPrice;

    /**
     * Construye un nuevo ítem de factura.
     *
     * @param product producto adquirido
     * @param qty cantidad del producto
     * @param unitPrice precio unitario del producto
     */
    public InvoiceItem(Product product, double qty, double unitPrice) {
        this.product = product;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    /**
     * Obtiene el producto de la línea de factura.
     *
     * @return producto adquirido
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Obtiene la cantidad adquirida del producto.
     *
     * @return cantidad comprada
     */
    public double getQty() {
        return qty;
    }

    /**
     * Obtiene el precio unitario aplicado al producto.
     *
     * @return precio unitario
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Calcula el importe total de la línea (precio unitario × cantidad).
     *
     * @return importe total de la línea
     */
    public double getLineTotal() {
        return unitPrice * qty;
    }
}
