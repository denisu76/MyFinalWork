package service;

import database.ProductRepository;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {

    @Test
    void checkAndSave() {
        ProductRepository repository = new ProductRepository();
        ProductService validationService = new ProductService();

        Product product1 = new Product(null, BigDecimal.valueOf(0.2), Category.FRUIT, BigDecimal.valueOf(0), "Tasty apples from Latvia");
        try {
            validationService.checkAndSave(repository, product1);
            assertTrue(false);
        } catch (IllegalArgumentException ex){
            assertTrue(true);
        }

        Product product2 = new Product("", BigDecimal.valueOf(0.2), Category.FRUIT, BigDecimal.valueOf(0), "Tasty apples from Latvia");
        try {
            validationService.checkAndSave(repository, product2);
            assertTrue(false);
        } catch (IllegalArgumentException ex){
            assertTrue(true);
        }

        Product product3 = new Product("Apple", BigDecimal.valueOf(0), Category.FRUIT, BigDecimal.valueOf(0), "Tasty apples from Latvia");
        try {
            validationService.checkAndSave(repository, product3);
            assertTrue(false);
        } catch (IllegalArgumentException ex){
            assertTrue(true);
        }

        Product product4 = new Product("Apple", BigDecimal.valueOf(0.2), null, BigDecimal.valueOf(0), "Tasty apples from Latvia");
        try {
            validationService.checkAndSave(repository, product4);
            assertTrue(false);
        } catch (IllegalArgumentException ex){
            assertTrue(true);
        }

    }

    @Test
    void findByCategory() {
        ProductRepository repository = new ProductRepository();
        ProductService validationService = new ProductService();
        Product product2 = new Product("Salmon", BigDecimal.valueOf(10), Category.FISH, BigDecimal.valueOf(0), "Big latvian fish");
        Product product3 = new Product("Apple", BigDecimal.valueOf(0.2), Category.FRUIT, BigDecimal.valueOf(0), "Tasty apples from Latvia");
        repository.save(product2);
        repository.save(product3);
        boolean flag = true;
        int count_fish = 0;
        for (Product product : validationService.findByCategory(repository,Category.FISH)){
            if (product.getCategory() != Category.FISH) {
                flag = false;
            }
            count_fish++;
        }
        assertTrue(flag);
        assertEquals(1,count_fish);
    }

    @Test
    void setPriceById() {
    }

    @Test
    void setDiscountById() {
    }

    @Test
    void setDiscountByCategory() {
    }
}