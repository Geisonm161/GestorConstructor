/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Representa una factura generada en el sistema.
 *
 * <p>
 * La clase {@code Invoice} almacena información relacionada a una transacción
 * realizada por un cliente, incluyendo los productos adquiridos, subtotal,
 * impuestos y el total a pagar.</p>
 *
 * <p>
 * Es una clase inmutable (excepto la lista de items, que depende de cómo se
 * gestione), diseñada para usarse como un objeto de transferencia de datos
 * (DTO).</p>
 *
 * Componentes de la factura:
 * <ul>
 * <li>{@code number}: número único de la factura</li>
 * <li>{@code dateTime}: fecha y hora de emisión</li>
 * <li>{@code customer}: cliente asociado a la compra</li>
 * <li>{@code items}: lista de productos y cantidades adquiridas</li>
 * <li>{@code subtotal}: valor total sin impuestos</li>
 * <li>{@code tax}: valor de los impuestos aplicados (puede ser 0 si no
 * aplica)</li>
 * <li>{@code total}: suma de subtotal + impuestos</li>
 * </ul>
 *
 * @author Geison Medina
 */
public class Invoice {

    /**
     * Número único de la factura.
     */
    private final String number;

    /**
     * Fecha y hora en que se generó la factura.
     */
    private final LocalDateTime dateTime;

    /**
     * Cliente al que pertenece la factura.
     */
    private final Usuario customer;

    /**
     * Lista de productos adquiridos junto con sus cantidades.
     */
    private final List<InvoiceItem> items;

    /**
     * Subtotal de la factura (sin impuestos).
     */
    private final double subtotal;

    /**
     * Impuesto aplicado a la factura (0 si no aplica).
     */
    private final double tax;

    /**
     * Total de la factura (subtotal + impuestos).
     */
    private final double total;

    /**
     * Construye una nueva factura con la información proporcionada.
     *
     * @param number número de la factura
     * @param dateTime fecha y hora de emisión
     * @param customer cliente asociado
     * @param items lista de productos adquiridos
     * @param subtotal monto sin impuestos
     * @param tax monto de impuestos (0 si no aplica)
     */
    public Invoice(String number, LocalDateTime dateTime, Usuario customer,
            List<InvoiceItem> items, double subtotal, double tax) {
        this.number = number;
        this.dateTime = dateTime;
        this.customer = customer;
        this.items = items;
        this.subtotal = subtotal;
        this.tax = tax;
        this.total = subtotal + tax;
    }

    /**
     * Obtiene el número único de la factura.
     *
     * @return número de la factura
     */
    public String getNumber() {
        return number;
    }

    /**
     * Obtiene la fecha y hora de emisión de la factura.
     *
     * @return fecha y hora de la factura
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }

    /**
     * Obtiene el cliente asociado a la factura.
     *
     * @return cliente de la factura
     */
    public Usuario getCustomer() {
        return customer;
    }

    /**
     * Obtiene la lista de productos y cantidades adquiridos en la factura.
     *
     * @return lista de {@code InvoiceItem}
     */
    public List<InvoiceItem> getItems() {
        return items;
    }

    /**
     * Obtiene el subtotal de la factura (sin impuestos).
     *
     * @return subtotal
     */
    public double getSubtotal() {
        return subtotal;
    }

    /**
     * Obtiene el valor de los impuestos aplicados.
     *
     * @return monto de impuestos
     */
    public double getTax() {
        return tax;
    }

    /**
     * Obtiene el total de la factura (subtotal + impuestos).
     *
     * @return monto total
     */
    public double getTotal() {
        return total;
    }
}
