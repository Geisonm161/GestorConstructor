/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Geison Medina
 */
public class Product {

    private String productName;
    private String productDescription;
    private float price;
    private String unit;
    private String imagePath;
    private String hardwareStoreName;
    private String address;
    private String contact;
    private int categoryId;
    private int id;

    /**
     * Asigna toda la información de un producto en una sola llamada.
     *
     * @param id identificador único del producto
     * @param productName nombre del producto
     * @param productDescription descripción del producto
     * @param price precio del producto
     * @param unit unidad de medida (ej. "kg", "saco", "unidad")
     * @param imagePath ruta de la imagen asociada al producto
     * @param hardwareStoreName nombre de la ferretería que vende el producto
     * @param address dirección de la ferretería
     * @param contact contacto de la ferretería (teléfono, email, etc.)
     * @param categoryId identificador de la categoría a la que pertenece el producto
     */
    public void setFullInfoProduct(int id,
                                   String productName,
                                   String productDescription,
                                   float price,
                                   String unit,
                                   String imagePath,
                                   String hardwareStoreName,
                                   String address,
                                   String contact,
                                   int categoryId) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.price = price;
        this.unit = unit;
        this.imagePath = imagePath;
        this.hardwareStoreName = hardwareStoreName;
        this.address = address;
        this.contact = contact;
        this.categoryId = categoryId;
    }

    /** @return identificador único del producto */
    public int getId() {
        return this.id;
    }

    /** @return nombre del producto */
    public String getProductName() {
        return this.productName;
    }

    /** @return descripción del producto */
    public String getProductDescription() {
        return this.productDescription;
    }

    /** @return precio del producto */
    public float getPrice() {
        return this.price;
    }

    /** @return unidad de medida del producto */
    public String getUnit() {
        return this.unit;
    }

    /** @return ruta de la imagen asociada al producto */
    public String getImagePath() {
        return this.imagePath;
    }

    /** @return nombre de la ferretería que vende el producto */
    public String getHardwareStoreName() {
        return this.hardwareStoreName;
    }

    /** @return dirección de la ferretería */
    public String getAddress() {
        return this.address;
    }

    /** @return contacto de la ferretería */
    public String getContact() {
        return this.contact;
    }

    /** @return identificador de la categoría del producto */
    public int getCategoryId() {
        return this.categoryId;
    }
}