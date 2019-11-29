package database;

import org.junit.jupiter.api.Test;
import service.Category;
import service.ItemNotFoundException;
import service.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    @Test
    void findAll() {
        ProductRepository repository = new ProductRepository();
        List<Product> findProducts = new ArrayList<Product>();
        Product product2 = new Product("Salmon", BigDecimal.valueOf(10), Category.FISH, BigDecimal.valueOf(0), "Big latvian fish");
        Product product3 = new Product("Apple", BigDecimal.valueOf(0.2), Category.FRUIT, BigDecimal.valueOf(0), "Tasty apples from Latvia");
        repository.save(product2);
        repository.save(product3);
        findProducts = repository.findAll();
        assertEquals(2,findProducts.size());
    }

    @Test
    void findById() {
        Product product4;
        ProductRepository repository = new ProductRepository();
        List<Product> findProducts = new ArrayList<Product>();
        Product product2 = new Product("Salmon", BigDecimal.valueOf(10), Category.FISH, BigDecimal.valueOf(0), "Big latvian fish");
        Product product3 = new Product("Apple", BigDecimal.valueOf(0.2), Category.FRUIT, BigDecimal.valueOf(0), "Tasty apples from Latvia");
        repository.save(product2);
        repository.save(product3);
        try{
          product4 = repository.findById(Long.valueOf(2));
          assertSame(product3, product4);
        } catch (ItemNotFoundException ex){
           assertTrue(false);
        }
    }

    @Test
    void save() {
        ProductRepository repository = new ProductRepository();
        Product product3 = new Product("Apple", BigDecimal.valueOf(0.2), Category.FRUIT, BigDecimal.valueOf(0), "Tasty apples from Latvia");
        repository.save(product3);
        for(Product product : repository.findAll()){
            assertSame(product3, product);
        }
    }

    @Test
    void delete() {
        ProductRepository repository = new ProductRepository();
        List<Product> findProducts = new ArrayList<Product>();
        Product product2 = new Product("Salmon", BigDecimal.valueOf(10), Category.FISH, BigDecimal.valueOf(0), "Big latvian fish");
        repository.save(product2);
        findProducts = repository.findAll();
        assertEquals(1,findProducts.size());
        try {
          repository.delete(Long.valueOf(1));
            findProducts = repository.findAll();
            assertEquals(0,findProducts.size());
        } catch (ItemNotFoundException ex){
          assertTrue(false);
        }
    }

}