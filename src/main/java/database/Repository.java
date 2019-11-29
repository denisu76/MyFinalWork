package database;

import java.util.List;
import service.*;

public interface Repository<T> {

    List<T> findAll();

    T findById(Long id) throws ItemNotFoundException;

    void save(T item) throws IllegalArgumentException;

    void delete(Long id) throws ItemNotFoundException;

}
