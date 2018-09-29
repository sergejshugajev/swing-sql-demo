package ru.kpfu.itis.entities;

import java.util.StringJoiner;

public class Product {

    private int id;
    private String name;
    private double price;
    private double weight;
    private Manufacturer manufacturer;
    private int categoryId;

    public Product(String name, double price, double weight, Manufacturer manufacturer, int category) {
        this(0, name, price, weight, manufacturer, category);
    }

    public Product(int id, String name, double price, double weight, Manufacturer manufacturer, int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.manufacturer = manufacturer;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categotyId) {
        this.categoryId = categotyId;
    }


    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("name='" + name + "'")
                .add("price=" + price)
                .add("weight=" + weight)
                .add("manufacturer=" + manufacturer)
                .add("categoryId=" + categoryId)
                .toString();
    }
}
