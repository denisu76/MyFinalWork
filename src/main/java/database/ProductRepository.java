package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import service.*;

public class ProductRepository implements Repository<Product>{
    private Map<Long, Product> database = new HashMap<Long, Product>();

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
        database.put(item.getId(), item);
    }

    public void delete(Long id) throws ItemNotFoundException{
        Product resultProduct;
        resultProduct = database.remove(id);
        if (resultProduct == null){
            throw new ItemNotFoundException("Can't delete this Product!");
        }
    }
}
