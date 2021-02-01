package RestAPI.Repository;

import RestAPI.Entity.NutritionScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * NutritionScore Repository JPA
 */
public interface NutritionScoreRepository extends JpaRepository<NutritionScore, Integer> {

    @Query(value = "SELECT n FROM NutritionScore n WHERE ?1 BETWEEN n.lower_bound AND n.upper_bound")
    NutritionScore findNutritionScore(int value);
}
