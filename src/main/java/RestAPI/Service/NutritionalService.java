package RestAPI.Service;

import RestAPI.Entity.Rule;
import RestAPI.Repository.NutritionScoreRepository;
import RestAPI.Repository.RuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.rmi.UnexpectedException;
import java.util.Map;

/**
 * Nutritional Service used to compute nutritional score
 */
@Service
public class NutritionalService implements  INutritionalService{

    private static final Logger logger = LoggerFactory.getLogger(NutritionalService.class);
    final RuleRepository ruleRepository;
    final NutritionScoreRepository nutritionScoreRepository;

    /**
     * NutritionalService Constructor
     *
     * @param ruleRepository rule Repository JPA
     * @param nutritionScoreRepository nutritionScore Repository JPA
     */
    public NutritionalService(RuleRepository ruleRepository, NutritionScoreRepository nutritionScoreRepository) {
        this.ruleRepository = ruleRepository;
        this.nutritionScoreRepository = nutritionScoreRepository;
    }

    /**
     * Computes the nutritional score of a product
     *
     * @param product which going to be "computed"
     * @return the nutritional score
     */
    public int computeNutritionalScore(Map<String, Object> product) {
        int nutritional_score = 0;

        // Get all rule names
        Iterable<String> rule_names = ruleRepository.findAllRuleNames();

        for (String rule_name : rule_names) {
            try {
                Map<String, Object> nutriments = (Map<String, Object>) product.get("nutriments");

                if (nutriments.containsKey(rule_name)) {
                    String value = nutriments.get(rule_name).toString();
                    nutritional_score += computeRule(rule_name, Double.parseDouble(value));
                } else {
                    logger.warn("Cannot find rule name {}", rule_name);
                }
            } catch (Exception e) {
                logger.error("Unexpected component value for {}", rule_name);
            }
        }
        return nutritional_score;
    }

    /**
     * Compute the Rule
     *
     * @param ruleName key
     * @param ruleValue value
     * @return the rule corresponding points
     * @throws UnexpectedException in case of problem
     */
    private int computeRule(String ruleName, double ruleValue) throws UnexpectedException {

        // Finding the rule
        Rule rule = ruleRepository.findPoints(ruleName, ruleValue);

        // If it's N or P rule
        switch (rule.getComponent()) {
            case 'N':
                return rule.getPoints();
            case 'P':
                return rule.getPoints() * -1 ;
            default:
                throw new UnexpectedException("Unexpected error");
        }
    }
}
