package app.ccb.domain.dtos.card;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "card")
@XmlAccessorType(XmlAccessType.FIELD)
public class CardImportDto {

    @XmlAttribute(name = "status")
    private String cardStatus;

    @XmlElement(name = "card-number")
    private String cardNumber;

    @XmlAttribute(name = "account-number")
    private String bankAccount;

    public CardImportDto() {
    }

    @NotNull
    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    @NotNull
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
