package app.entities;

import app.entities.billing_details.BillingDetail;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BasicEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<BillingDetail> billingDetails;

    public User() {
        setBillingDetails(new HashSet<>());
    }

    public User(String firstName, String lastname, String email, String password) {
        setFirstName(firstName);
        setLastName(lastname);
        setEmail(email);
        setPassword(password);
        setBillingDetails(new HashSet<>());
    }

    @Column(name = "first_name", length = 50)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "las_name", length = 50)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    @Column(name = "email")
    @Email
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "password")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @OneToMany(mappedBy = "owner", targetEntity = BillingDetail.class)
    public Set<BillingDetail> getBillingDetails() {
        return this.billingDetails;
    }

    public void setBillingDetails(Set<BillingDetail> billingDetails) {
        this.billingDetails = billingDetails;
    }
}
