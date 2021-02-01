package RestAPI.Entity.Factory;

import RestAPI.Entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductFactory implements IProductFactory {

    @Override
    public Product create() {
        return new Product();
    }
}
