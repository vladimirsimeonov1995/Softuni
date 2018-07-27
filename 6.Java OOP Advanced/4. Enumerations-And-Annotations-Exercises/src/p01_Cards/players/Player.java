package p01_Cards.players;

import p01_Cards.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements Comparable<Player> {

    private String name;
    private List<Card> cards;

    public Player(String name){
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public Card getBiggestCard(){
        Collections.sort(cards);

        return cards.get(cards.size() -1);
    }

    public void addCard(Card card){
        cards.add(card);
    }


    @Override
    public int compareTo(Player secondPlayer) {
        return this.getBiggestCard().compareTo(secondPlayer.getBiggestCard());
    }

}
