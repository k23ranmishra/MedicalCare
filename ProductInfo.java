package rkm.ecom;


import rkm.ecom.Product;

public class ProductInfo {
    private String code;
    private String name;
    private String description1;
    private double price;

    public ProductInfo() {
    }

    public ProductInfo(Product product) {
        this.code = product.getCode();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description1 = product.getDescription1();
    }

    // Using in JPA/Hibernate query
    public ProductInfo(String code, String name,  double price, String description1) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.description1 = description1 ;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }
    

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
