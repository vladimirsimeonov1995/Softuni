package entities.ingredients.shampoos;

import entities.ingredients.basicingradients.BasicIngredient;
import entities.ingredients.labels.BasicLabel;
import enums.Size;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "shampoos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "shampoo_type", discriminatorType = DiscriminatorType.STRING)
public class BasicShampoo {

    private long id;

    private BigDecimal price;

    private String brand;

    private Size size;

    private BasicLabel label;

    private Set<BasicIngredient> ingredients;

    protected BasicShampoo(){
        setIngredients(new HashSet<>());
    }

    public BasicShampoo(String brand, BigDecimal price, Size size, BasicLabel label) {
        this.brand = brand;
        this.price = price;
        this.size = size;
        this.label = label;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return this.id;
    }

    @Basic
    public BigDecimal getPrice() {
        return this.price;
    }


    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Enumerated
    public Size getSize() {
        return this.size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @OneToOne(
            optional = true,
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "label", referencedColumnName = "id")
    public BasicLabel getLabel() {
        return this.label;
    }

    public void setLabel(BasicLabel label) {
        this.label = label;
    }

    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "shampoos_ingredients",
            joinColumns = @JoinColumn(
                    name = "shampoo_id",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "ingredient_id",
                    referencedColumnName = "id"
            )
    )
    public Set<BasicIngredient> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(Set<BasicIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setId(long id) {
        this.id = id;
    }
}