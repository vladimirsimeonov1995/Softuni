package p01_Cards;

import p01_Cards.cards.Card;
import p01_Cards.cards.CardDesk;
import p01_Cards.players.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Player firstPlayer = new Player(reader.readLine());
        Player secondPlayer = new Player(reader.readLine());

        CardDesk desk = new CardDesk();

        getPlayersCards(reader, firstPlayer, desk);

        getPlayersCards(reader, secondPlayer, desk);

        Player winner = getWinner(firstPlayer, secondPlayer);

        System.out.printf("%s wins with %s.", winner.getName(), winner.getBiggestCard());


    }

    private static void getPlayersCards(BufferedReader reader, Player secondPlayer, CardDesk desk) throws IOException {
        while (secondPlayer.getCards().size() < 5) {
            String[] rankAndSuit = reader.readLine().split(" of ");

            Card card = desk.getCard(rankAndSuit[0], rankAndSuit[1]);

            if (card != null)
                secondPlayer.addCard(card);
        }
    }

    private static Card getCard(BufferedReader reader) throws IOException {
        return new Card(reader.readLine(), reader.readLine());
    }

    private static void printTheBiggestCard(Card... cards) {
        Card biggestCard = new Card("TWO", "CLUBS");

        for (Card card : cards) {
            if (card.compareTo(biggestCard) >= 0)
                biggestCard = card;
        }

        System.out.println(biggestCard);
    }

    private static Player getWinner(Player firstPlayer, Player secondPlayer) {

        if (firstPlayer.compareTo(secondPlayer) >= 0) {
            return firstPlayer;
        } else
            return secondPlayer;

    }

}
