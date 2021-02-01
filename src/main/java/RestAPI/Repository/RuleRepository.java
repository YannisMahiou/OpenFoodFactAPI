package RestAPI.Repository;

import org.springframework.data.jpa.repository.Query;
import RestAPI.Entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Rule Repository JPA
 */
public interface RuleRepository extends JpaRepository<Rule, Integer> {
    @Query(value = "SELECT DISTINCT name FROM rule", nativeQuery = true)
    Iterable<String> findAllRuleNames();

    @Query(value = "select * from Rule where name like ?1 and min_bound <= ?2 order by min_bound desc fetch first rows only", nativeQuery = true)
    Rule findPoints(String name, double min_bound);
}