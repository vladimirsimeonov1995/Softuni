package app.entities.billing_details;

import app.entities.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bank_acount")
public class BankAcount extends BillingDetail {

    private String name;
    private String SWIFTcode;

    public BankAcount() {
    }

    public BankAcount(String number, User owner, String name, String SWIFTcode) {
        super(number, owner);
        setName(name);
        setSWIFTcode(SWIFTcode);
    }

    @Column(name = "name")
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "SWIFT_code")
    public String getSWIFTcode() {
        return this.SWIFTcode;
    }

    public void setSWIFTcode(String SWIFTcode) {
        this.SWIFTcode = SWIFTcode;
    }
}
