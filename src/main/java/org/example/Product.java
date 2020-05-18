package org.example;

public class Product {
    private long id;
    private String name;
    private long price;

    public Product(long id, String name, long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }


}
