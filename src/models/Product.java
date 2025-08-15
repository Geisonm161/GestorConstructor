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
    private String price;
    private String unit;
    private String imagePath;
    private String hardwareStoreName;
    private String address;
    private String contact;
    private int categoryId;

    public void setFullInfoProduct(String productName, String productDescription, String price, String unit, String imagePath, String hardwareStoreName, String address, String contact, int categoryId) {
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

    public String getProductName() {
        return this.productName;
    }

    public String getProductDescription() {
        return this.productDescription;
    }

    public String getPrice() {
        return this.price;
    }

    public String getUnit() {
        return this.unit;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public String getHardwareStoreName() {
        return this.hardwareStoreName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getContact() {
        return this.contact;
    }

    public int getCategoryId() {
        return this.categoryId;
    }
}
