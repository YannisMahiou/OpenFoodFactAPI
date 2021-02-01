package RestAPI.Service;

import RestAPI.Entity.NutritionScore;
import RestAPI.Entity.Product;
import RestAPI.Repository.NutritionScoreRepository;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import java.util.Map;

/**
 * Product Service used to get product from OpenFoodFact API
 */
@Service
public class ProductService implements IProductService{
    private static final String BASE_URL = "https://fr.openfoodfacts.org/api/v0/produit/";
    private final RestTemplate restTemplate;
    private final NutritionScoreRepository nutritionScoreRepository;
    private final INutritionalService nutritionalService;

    /**
     * Product Service Constructor
     *
     * @param restTemplate OpenAPI template communicator
     * @param nutritionScoreRepository nutrition score repository
     * @param nutritionalService used to compute the nutritional score
     */
    public ProductService(RestTemplate restTemplate, NutritionScoreRepository nutritionScoreRepository, NutritionalService nutritionalService) {
        this.restTemplate = restTemplate;
        this.nutritionScoreRepository = nutritionScoreRepository;
        this.nutritionalService = nutritionalService;
    }

    /**
     * Get the product from OpenFoodFact API and compute the
     * Nutritional Score, Color and associated class
     *
     * @param product product to fill from the OpenAPI
     * @param prdId product identifier
     */
    public void getProduct(Product product, String prdId) {

        JSONObject json = restTemplate.getForObject(BASE_URL + prdId, JSONObject.class);

        // If product exist in OpenFoodFacts API
        if (json != null && json.containsKey("product")) {
            Map<String, Object> productJSON = (Map<String, Object>) json.get("product");

            // Update product
            product.updateNameAndBarCode(productJSON);
            product.setNutritionScore(nutritionalService.computeNutritionalScore(productJSON));
            NutritionScore nutritionScore = nutritionScoreRepository.findNutritionScore(product.getNutritionScore());

            // Compute Nutritional score
            product.computeClassAndColor(nutritionScore);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
    }
}