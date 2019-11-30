package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.*;

public class ProductRepository implements Repository<Product>{
    private Map<Long, Product> database = new HashMap<Long, Product>();
    private static Long counter = Long.valueOf(0);

    public List<Product> findAll(){
        return new ArrayList<Product>(database.values());
    }

    public Product findById(Long id) throws ItemNotFoundException{
        Product resultProduct;
        resultProduct = database.get(id);
        if (resultProduct == null){
            throw new ItemNotFoundException("Can't find this product!");
        }
        return resultProduct;
    }

    public void save(Product item) throws IllegalArgumentException{
        if (item.getId() != Long.valueOf(0)){
            database.put(item.getId(), item);
        } else {
            counter++;
            item.setId(Long.valueOf(counter));
            database.put(Long.valueOf(counter), item);
        }
    }

    public void delete(Long id) throws ItemNotFoundException{
        Product resultProduct;
        resultProduct = database.remove(id);
        if (resultProduct == null){
            throw new ItemNotFoundException("Can't delete this Product!");
        }
    }

    public static void setCounter(Long counter) {
        ProductRepository.counter = counter;
    }
}
