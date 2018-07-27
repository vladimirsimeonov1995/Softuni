package p01_Cards.cards;

import p01_Cards.enums.CustomAnnotation;

@CustomAnnotation(category = "Suit",description = "Provides suit constants for a Card class.")
public enum CardSuit {

    CLUBS(0), DIAMONDS(13), HEARTS(26), SPADES(39);

    int power;

    CardSuit(int power){
        this.power = power;
    }

}
