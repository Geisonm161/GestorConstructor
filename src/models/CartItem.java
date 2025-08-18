/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Geison Medina
 */
public class CartItem {

    /**
     * Producto asociado a este ítem del carrito.
     */
    private final Product product;

    /**
     * Cantidad del producto en el carrito.
     */
    private double quantity;

    /**
     * Crea un nuevo ítem del carrito con un producto y una cantidad específica.
     *
     * @param product el producto que se está agregando al carrito (no debe ser
     * null).
     * @param quantity la cantidad del producto (debe ser mayor a 0).
     */
    public CartItem(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     * Obtiene el producto asociado a este ítem.
     *
     * @return el producto del carrito.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Obtiene la cantidad actual del producto en el carrito.
     *
     * @return cantidad de producto.
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Modifica la cantidad del producto en el carrito.
     *
     * @param q nueva cantidad del producto (debe ser mayor a 0).
     */
    public void setQuantity(double q) {
        this.quantity = q;
    }

    /**
     * Calcula el total de la línea (precio del producto por cantidad).
     *
     * @return el costo total de este ítem en el carrito.
     */
    public double getLineTotal() {
        return product.getPrice() * quantity; // ajusta si usas BigDecimal
    }
}
