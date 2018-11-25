package app.entities.billing_details;

import app.entities.BasicEntity;
import app.entities.User;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BillingDetail extends BasicEntity {

    private String number;
    private User owner;

    public BillingDetail() {
    }

    public BillingDetail(String number, User owner) {
        this.number = number;
        this.owner = owner;
    }

    @Column(name = "number")
    public String getNumber() {
        return this.number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    public User getOwner() {
        return this.owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
