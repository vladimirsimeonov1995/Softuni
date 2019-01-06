package softuni.advancedquerylab.domain.entities;

import softuni.advancedquerylab.domain.entities.abstractions.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "labels")
public class Label extends BaseEntity {

    private String title;
    private String subtitle;
    private Set<Shampoo> shampoo;

    public Label() {
    }

    @Column(name = "title")
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "subtitle")
    public String getSubtitle() {
        return this.subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    @OneToMany(mappedBy = "label", targetEntity = Shampoo.class, cascade = CascadeType.ALL)
    public Set<Shampoo> getShampoo() {
        return this.shampoo;
    }

    public void setShampoo(Set<Shampoo> shampoo) {
        this.shampoo = shampoo;
    }
}
