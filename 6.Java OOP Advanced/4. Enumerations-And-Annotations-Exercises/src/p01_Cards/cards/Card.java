package p01_Cards.cards;

public class Card implements Comparable<Card> {

    private CardRank cardRank;
    private CardSuit cardSuit;
    private int cardPower;

    public Card(String cardRank, String cardSuit) {
        this.cardRank = Enum.valueOf(CardRank.class, cardRank);
        this.cardSuit = Enum.valueOf(CardSuit.class,cardSuit);
        setCardPower();
    }

    public int getCardPower() {
        return this.cardPower;
    }

    private void setCardPower(){
        this.cardPower = this.cardSuit.power + this.cardRank.power;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.cardRank.name()).append(" of ")
                .append(this.cardSuit.name());
        return sb.toString();
    }


    @Override
    public int compareTo(Card card) {
        return Integer.compare(this.cardPower,card.cardPower);
    }
}
