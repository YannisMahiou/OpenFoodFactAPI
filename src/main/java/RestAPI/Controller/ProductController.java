package RestAPI.Controller;

import RestAPI.Entity.Factory.IProductFactory;
import RestAPI.Entity.Factory.ProductFactory;
import RestAPI.Entity.Product;
import RestAPI.Repository.ProductRepository;
import RestAPI.Service.IProductService;
import RestAPI.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Rest Controller for products requests
 */
@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductRepository productRepository;
    private final IProductService productService;
    private final IProductFactory factory;

    /**
     * ProductController Constructor
     *
     * @param productRepository Injected Product Repository
     * @param productService Injected Product Service
     * @param factory Injected Product Factory
     */
    public ProductController(ProductRepository productRepository, ProductService productService, ProductFactory factory) {
        this.productRepository = productRepository;
        this.productService = productService;
        this.factory = factory;
    }

    /**
     * Get a product (get it from OpenFoodFactAPI if it does not exist)
     *
     * @param prdId the barcode
     * @return the product or 404 error
     */
    @GetMapping(value = "/product/{barCode}")
    public @ResponseBody Product getProductById(@PathVariable("barCode") String prdId) {
        Product product = productRepository.findOneByBarCode(prdId);

        if (product == null) {
            product = factory.create();
            productService.getProduct(product, prdId);
            productRepository.save(product);
            logger.info("Created new Product : {}", product.getName());
        }
        else {
            logger.info("Found Product : {}", product.getName());
            productService.getProduct(product, prdId);
            productRepository.save(product);
            logger.info("Updated Product : {}", product.getName());
        }
        return product;
    }
}