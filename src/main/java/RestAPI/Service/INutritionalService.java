package RestAPI.Service;

import java.util.Map;

/**
 * Nutritional Service abstraction interface
 */
public interface INutritionalService {

     /**
      * Computes the nutritional score of a product
      *
      * @param product which going to be "computed"
      * @return the nutritional score
      */
     int computeNutritionalScore(Map<String, Object> product);


}
