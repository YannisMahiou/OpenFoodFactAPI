package RestAPI.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Map;

@Entity
@Table(name = "product")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "barCode", unique = true)
    private String barCode;

    @Column(name = "nutrition_score")
    private int nutritionScore;

    @Column(name = "color")
    private String color;

    @Column(name = "classe")
    private String classe;

    public Product() { }

    /**
     * Updates the name and the bar code of the product
     *
     * @param json received from the OpenFoodFactAPI
     */
    public void updateNameAndBarCode(Map<String, Object> json) {
        this.name = (String) json.get("generic_name") + " - " + json.get("product_name_fr");
        this.barCode = (String) json.get("_id");
    }

    /**
     * Sets the class and the color of a product
     *
     * @param nutritionScore entity containing the class and color
     */
    public void computeClassAndColor(NutritionScore nutritionScore) {
        classe = nutritionScore.getClasse();
        color = nutritionScore.getColor();
    }

    public int getId() {
        return this.id;
    }

    public String getBarCode() {
        return this.barCode;
    }

    public String getName() {
        return this.name;
    }

    public int getNutritionScore() {
        return nutritionScore;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setNutritionScore(int nutritionScore) {
        this.nutritionScore = nutritionScore;
    }

}