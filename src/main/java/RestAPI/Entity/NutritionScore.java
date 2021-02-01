package RestAPI.Entity;

import javax.persistence.*;

@Entity
@Table(name = "NutritionScore")
public class NutritionScore {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "classe")
    private String classe;

    @Column(name = "lower_bound")
    private int lower_bound;

    @Column(name = "upper_bound")
    private int upper_bound;

    @Column(name = "color")
    private String color;

    public NutritionScore() { }

    public long getId() {
        return id;
    }

    public String getClasse() {
        return classe;
    }

    public int getLower_bound() {
        return lower_bound;
    }

    public int getUpper_bound() {
        return upper_bound;
    }

    public String getColor() {
        return color;
    }
}