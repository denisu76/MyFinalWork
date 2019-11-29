package service;

import database.*;

import java.math.BigDecimal;
import java.util.ArrayList;

public class ProductService {
    public void checkAndSave(ProductRepository repository, Product item) throws IllegalArgumentException{
        if (item.getName() == null){
            throw new IllegalArgumentException("Name must be not null!");
        }
        if (item.getName().isBlank()){
            throw new IllegalArgumentException("Name must be not empty!");
        }
        if (item.getPrice() == null){
            throw new IllegalArgumentException("Price must be not null!");
        }
        if (item.getPrice().compareTo(BigDecimal.valueOf(0)) <= 0)
        {
            throw new IllegalArgumentException("Price must be > 0!");
        }
        if (item.getCategory() == null){
            throw new IllegalArgumentException("Category must be not null!");
        }
        repository.save(item);
    }

    public void printAll(ProductRepository repository){
        System.out.println("All products:");
        for (Product product : repository.findAll()){
            product.productInformation();
        }
    }

    public ArrayList<Product> findByCategory(ProductRepository repository, Category category){
        ArrayList<Product> findProducts = new ArrayList<Product>();
        for (Product product : repository.findAll()){
            if (product.getCategory().equals(category)){
                //System.out.println(book.toString());
                findProducts.add(product);
            }
        }
        return findProducts;
    }

    public void setPriceById(ProductRepository repository, Long id, BigDecimal price){
        Product product;
        try {
            product = repository.findById(id);
            product.setPrice(price);
            product.setActualPrice((price.subtract(price.multiply(product.getDiscount()))).setScale(2, BigDecimal.ROUND_HALF_UP));
            repository.save(product);
        } catch (ItemNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void setDiscountById(ProductRepository repository, Long id, BigDecimal discount){
        Product product;
        try {
            product = repository.findById(id);
            product.setDiscount(discount);
            product.setActualPrice((product.getPrice().subtract(product.getPrice().multiply(discount))).setScale(2, BigDecimal.ROUND_HALF_UP));
            repository.save(product);
        } catch (ItemNotFoundException ex){
            System.out.println(ex.getMessage());
        }
    }

    public void setDiscountByCategory(ProductRepository repository, Category category, BigDecimal discount){
        ArrayList<Product> categoryList;
        categoryList = findByCategory(repository, category);
        for (Product product : categoryList){
            setDiscountById(repository, product.getId(), discount);
        }
    }

}
