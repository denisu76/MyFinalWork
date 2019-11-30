package service;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String name;
    private Long id;
    private BigDecimal price;
    private BigDecimal actualPrice;
    private Category category;
    private BigDecimal discount;
    private String description;
    //private static Long counter = Long.valueOf(0);

    public Product(String name, BigDecimal price, Category category, BigDecimal discount, String description) {
        //counter ++;
        //this.id = counter;
        this.id = Long.valueOf(0);
        this.name = name;
        this.price = price;
        this.category = category;
        this.discount = discount;
        this.description = description;
        actualPrice = (price.subtract(price.multiply(discount))).setScale(2, BigDecimal.ROUND_HALF_UP);
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name) &&
                price.equals(product.price) &&
                category == product.category &&
                Objects.equals(discount, product.discount) &&
                Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, category, discount, description);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", price=" + price +
                ", category=" + category +
                ", discount=" + discount.multiply(BigDecimal.valueOf(100)) + "%" +
                ", actualPrice=" + actualPrice +
                ", description='" + description + '\'' +
                '}';
    }

    public void productInformation(){
        System.out.println("Product information:");
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Regular price: " + price);
        System.out.println("Discount: " + discount.multiply(BigDecimal.valueOf(100)) + "%");
        System.out.println("Actual price: " + actualPrice);
        System.out.println("Category: " + category);
        System.out.println("Description: " + description);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setActualPrice(BigDecimal actualPrice) {
        this.actualPrice = actualPrice;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public void setDescription(String description) {
        this.description = description;
    }
 /*
    public static void setCounter(Long counter) {
        Product.counter = counter;
    }

 */

    public void setId(Long id) {
        this.id = id;
    }
}
