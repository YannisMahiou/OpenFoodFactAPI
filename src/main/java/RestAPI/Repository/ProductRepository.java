package RestAPI.Repository;

import RestAPI.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Product Repository JPA
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findOneByBarCode(String barcode);
}
