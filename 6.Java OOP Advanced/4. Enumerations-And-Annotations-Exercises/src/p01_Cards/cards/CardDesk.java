package p01_Cards.cards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardDesk {

    private Map<Integer,Card> cards;

    public CardDesk(){
        this.cards = new HashMap<>();
        this.setCards();
    }

    private void setCards(){
        for (CardRank rank : CardRank.values()) {
            for (CardSuit suit : CardSuit.values()) {
                Card newCard = new Card(rank.name(),suit.name());
                cards.put(newCard.getCardPower(),newCard);
            }
        }
    }

    public Card getCard(String rank,String suit){

        try {
            Card card = new Card(rank,suit);
            if(cards.containsKey(card.getCardPower())) {
                cards.remove(card.getCardPower());
                return card;
            }

            System.out.println("Card is not in the deck.");
        }catch (Exception ex){
            System.out.println("No such card exists.");
        }

        return null;

    }

}
