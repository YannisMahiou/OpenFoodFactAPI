package RestAPI.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Rule")
public class Rule {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "points")
    private int points;

    @Column(name = "min_bound")
    private double min_bound;

    @Column(columnDefinition = "CHAR CHECK (component in ('N', 'P'))")
    private Character component;

    public Rule() { }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public double getMin_bound() {
        return min_bound;
    }

    public Character getComponent() {
        return component;
    }
}
