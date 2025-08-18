/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Geison Medina
 */
public class ShoppingCart {

    /**
     * Clase interna que representa un ítem simple con producto y cantidad.
     * <p>Se usa en contextos internos cuando no se requiere toda la lógica
     * de {@link CartItem}.</p>
     */
    public static class Item {

        private final Product product;
        private double qty;

        /**
         * Crea un ítem con producto y cantidad.
         * 
         * @param product producto asociado
         * @param qty cantidad seleccionada
         */
        public Item(Product product, double qty) {
            this.product = product;
            this.qty = qty;
        }

        /** @return el producto de este ítem */
        public Product getProduct() {
            return product;
        }

        /** @return cantidad asociada al producto */
        public double getQty() {
            return qty;
        }

        /**
         * Establece la cantidad del ítem.
         * 
         * @param qty nueva cantidad
         */
        public void setQty(double qty) {
            this.qty = qty;
        }
    }

    /** Instancia única (patrón Singleton). */
    private static final ShoppingCart INSTANCE = new ShoppingCart();

    /** Lista principal de ítems en el carrito. */
    private final List<CartItem> items = new ArrayList<>();

    /** Tasa de ITBIS (impuesto) por defecto (18%). */
    private double taxRate = 0.18;

    /** Constructor privado: solo accesible dentro de la clase. */
    private ShoppingCart() {
    }

    /**
     * Obtiene la instancia única del carrito.
     * 
     * @return instancia global de {@code ShoppingCart}
     */
    public static ShoppingCart get() {
        return INSTANCE;
    }

    /**
     * Devuelve una vista inmutable de los ítems actuales en el carrito.
     * 
     * @return lista de {@link CartItem}
     */
    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    /**
     * Indica si el carrito está vacío.
     * 
     * @return {@code true} si no hay ítems en el carrito
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /** Elimina todos los ítems del carrito. */
    public void clear() {
        items.clear();
    }

    /**
     * Agrega un producto al carrito.
     * <p>Si ya existe en el carrito, aumenta su cantidad.</p>
     * 
     * @param p producto a agregar
     * @param qty cantidad a agregar
     */
    public void add(Product p, double qty) {
        if (p == null || qty <= 0) {
            return;
        }

        for (CartItem it : items) {
            if (it.getProduct().getId() == p.getId()) { 
                it.setQuantity(it.getQuantity() + qty);
                return;
            }
        }
        items.add(new CartItem(p, qty));
    }

    /**
     * Elimina un producto del carrito.
     * 
     * @param p producto a eliminar
     */
    public void remove(Product p) {
        if (p == null) {
            return;
        }
        items.removeIf(it -> it.getProduct().getId() == p.getId());
    }

    /**
     * Calcula el subtotal (suma de todos los ítems sin impuestos).
     * 
     * @return subtotal de la compra
     */
    public double getSubtotal() {
        return items.stream().mapToDouble(CartItem::getLineTotal).sum();
    }

    /**
     * Calcula el valor del impuesto aplicado al subtotal.
     * 
     * @return monto del impuesto
     */
    public double getTax() {
        return getSubtotal() * taxRate;
    }

    /**
     * Calcula el total (subtotal + impuesto).
     * 
     * @return total a pagar
     */
    public double getTotal() {
        return getSubtotal() + getTax();
    }

    /**
     * Obtiene la tasa de impuesto actual.
     * 
     * @return tasa de ITBIS en formato decimal (ej: 0.18 = 18%)
     */
    public double getTaxRate() {
        return taxRate;
    }

    /**
     * Establece la tasa de impuesto usada en los cálculos.
     * 
     * @param taxRate nueva tasa de impuesto
     */
    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }
}