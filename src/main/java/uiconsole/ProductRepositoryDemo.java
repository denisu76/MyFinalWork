package uiconsole;

import java.math.BigDecimal;
import service.*;
import database.*;

public class ProductRepositoryDemo {

    public static void main(String[] args) throws ItemNotFoundException, IllegalArgumentException{
        ProductRepository repository = new ProductRepository();
        ProductService validationService = new ProductService();

        System.out.println("Creating of products");
        Product product1 = new Product(null, BigDecimal.valueOf(10), Category.FRUIT, BigDecimal.valueOf(0), "");
        Product product2 = new Product("Salmon", BigDecimal.valueOf(10), Category.FISH, BigDecimal.valueOf(0), "Big latvian fish");
        Product product3 = new Product("Apple", BigDecimal.valueOf(0.2), Category.FRUIT, BigDecimal.valueOf(0), "Tasty apples from Latvia");
        Product product4 = new Product("Mackerel", BigDecimal.valueOf(1), Category.FISH, BigDecimal.valueOf(0.111), "");
        Product product5 = new Product("Annele", BigDecimal.valueOf(1.98), Category.MILK, BigDecimal.valueOf(0.5), "Milk from Lithuania");

        System.out.println("Save of products where name = null");
        try {
            validationService.checkAndSave(repository, product1);
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("Save of products with mandatory fields");
        try {
            validationService.checkAndSave(repository, product2);
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        try {
            validationService.checkAndSave(repository, product3);
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        try {
            validationService.checkAndSave(repository, product4);
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }
        try {
            validationService.checkAndSave(repository, product5);
        } catch (IllegalArgumentException ex){
            System.out.println(ex.getMessage());
        }

        validationService.printAll(repository);

        System.out.println("Find by FISH:");
        for (Product product : validationService.findByCategory(repository,Category.FISH)){
            product.productInformation();
        }

        try {
            System.out.println("repository.findById(2) = " + repository.findById(Long.valueOf(2)));
        } catch (ItemNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("Delete of (2)");
        try {
            repository.delete(Long.valueOf(2));
        } catch (ItemNotFoundException ex){
            System.out.println(ex.getMessage());
        }

        validationService.printAll(repository);

        System.out.println("Finf by id of (2)");
        try {
            System.out.println("repository.findById(2) = " + repository.findById(Long.valueOf(2)));
        } catch (ItemNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        System.out.println("Delete of (2)");
        try {
            repository.delete(Long.valueOf(2));
        } catch (ItemNotFoundException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("new price = 2 for (3)");
        validationService.setPriceById(repository, Long.valueOf(3), BigDecimal.valueOf(2));
        try {
            System.out.println("repository.findById(3) = " + repository.findById(Long.valueOf(3)));
        } catch (ItemNotFoundException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("new discount = 10% for (3)");
        validationService.setDiscountById(repository, Long.valueOf(3), BigDecimal.valueOf(0.1));
        try {
            System.out.println("repository.findById(3) = " + repository.findById(Long.valueOf(3)));
        } catch (ItemNotFoundException ex){
            System.out.println(ex.getMessage());
        }

        System.out.println("set discount = 60% for FISH");
        validationService.setDiscountByCategory(repository, Category.FISH, BigDecimal.valueOf(0.6));
        validationService.printAll(repository);

    }
}
