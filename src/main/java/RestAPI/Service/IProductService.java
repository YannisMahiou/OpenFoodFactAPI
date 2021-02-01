package RestAPI.Service;

import RestAPI.Entity.Product;

/**
 * Product Service abstraction interface
 */
public interface IProductService {

    /**
     * Get the product from OpenFoodFact API and compute the
     * Nutritional Score, Color and associated class
     *
     * @param product product to fill from the OpenAPI
     * @param prdId product identifier
     */
    void getProduct(Product product, String prdId);
}
