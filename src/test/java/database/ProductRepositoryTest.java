package database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    @Test
    void findAll() {
        ProductRepository repository = new ProductRepository();
        assertNotNull(repository.findAll());

    }
/*
    @Test
    void findById() {
    }

    @Test
    void save() {
    }

    @Test
    void delete() {
    }
*/
}