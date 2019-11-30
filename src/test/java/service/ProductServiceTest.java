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
        ProductRepository repository = new ProductRepository();
        ProductService validationService = new ProductService();
        ProductRepository.setCounter(Long.valueOf(0));
        Product product2 = new Product("Salmon", BigDecimal.valueOf(10), Category.FISH, BigDecimal.valueOf(0), "Big latvian fish");
        Product product3 = new Product("Apple", BigDecimal.valueOf(0.2), Category.FRUIT, BigDecimal.valueOf(0), "Tasty apples from Latvia");
        repository.save(product2);
        repository.save(product3);

        validationService.setPriceById(repository,Long.valueOf(2),BigDecimal.valueOf(100));
        try {
                BigDecimal oldPrice = repository.findById(Long.valueOf(2)).getPrice();
                BigDecimal newPrice = BigDecimal.valueOf(100);
                assertTrue(oldPrice.compareTo(newPrice) == 0);
        } catch (ItemNotFoundException ex){
                assertTrue(false);
        }

    }

    @Test
    void setDiscountById() {
        ProductRepository repository = new ProductRepository();
        ProductService validationService = new ProductService();
        ProductRepository.setCounter(Long.valueOf(0));
        Product product2 = new Product("Salmon", BigDecimal.valueOf(10), Category.FISH, BigDecimal.valueOf(0.2), "Big latvian fish");
        Product product3 = new Product("Apple", BigDecimal.valueOf(0.2), Category.FRUIT, BigDecimal.valueOf(0.3), "Tasty apples from Latvia");
        repository.save(product2);
        repository.save(product3);

        validationService.setDiscountById(repository,Long.valueOf(2),BigDecimal.valueOf(0.5));
        try {
                BigDecimal oldDiscount = repository.findById(Long.valueOf(2)).getDiscount();
                BigDecimal newDiscount = BigDecimal.valueOf(0.5);
                assertTrue(oldDiscount.compareTo(newDiscount) == 0);
        } catch (ItemNotFoundException ex){
                assertTrue(false);
        }

    }

    @Test
    void setDiscountByCategory() {
        ProductRepository repository = new ProductRepository();
        ProductService validationService = new ProductService();
        Product product1 = new Product("Banana", BigDecimal.valueOf(5), Category.FRUIT, BigDecimal.valueOf(0), "");
        Product product2 = new Product("Salmon", BigDecimal.valueOf(10), Category.FISH, BigDecimal.valueOf(0), "Big latvian fish");
        Product product3 = new Product("Apple", BigDecimal.valueOf(0.2), Category.FRUIT, BigDecimal.valueOf(0), "Tasty apples from Latvia");
        Product product4 = new Product("Mackerel", BigDecimal.valueOf(1), Category.FISH, BigDecimal.valueOf(0), "");
        Product product5 = new Product("Annele", BigDecimal.valueOf(1.98), Category.MILK, BigDecimal.valueOf(0), "Milk from Lithuania");
        repository.save(product1);
        repository.save(product2);
        repository.save(product3);
        repository.save(product4);
        repository.save(product5);
        validationService.setDiscountByCategory(repository, Category.FISH, BigDecimal.valueOf(0.6));
        boolean flag = true;
        BigDecimal fishDiscount = BigDecimal.valueOf(0.6);
        BigDecimal notFishDiscount = BigDecimal.valueOf(0);
        for (Product product : repository.findAll()){
            BigDecimal repositoryDiscount = product.getDiscount();
            if ((product.getCategory() == Category.FISH) && !(repositoryDiscount.compareTo(fishDiscount) == 0)){
                flag = false;
            }
            if ((product.getCategory() != Category.FISH) && !(repositoryDiscount.compareTo(notFishDiscount) == 0)){
                flag = false;
            }
            assertTrue(flag);
        }
    }
}