package com.blackjack.game.deck;

import java.util.*;

public class DeckOfCards {
    private static final int DECK_SIZE = 52;

    private long seed;
    private List<Card> cards;
    private int top = 0;

    private DeckOfCards(long seed) {
        this.seed = seed;
        Card[] cardsWithoutShirt = new Card[DECK_SIZE];
        System.arraycopy(Card.values(), 1, cardsWithoutShirt, 0, DECK_SIZE);
        cards = Arrays.asList(cardsWithoutShirt);
        Collections.shuffle(cards, new Random(seed));
    }

    public static DeckOfCards shuffle(long seed) {
        return new DeckOfCards(seed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DeckOfCards)) return false;

        DeckOfCards that = (DeckOfCards) o;

        return seed == that.seed;

    }

    @Override
    public int hashCode() {
        return (int) (seed ^ (seed >>> 32));
    }

    public long getSeed() {
        return seed;
    }

    public Card getNextCard() {
        if (top >= DECK_SIZE) {
            throw new NoSuchElementException("The deck of cards is empty.");
        }
        return cards.get(top++);
    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder();
        for (int i = 0; i < DECK_SIZE; i++) {
            Card card = cards.get(i);
            int j = i + 1;
            if(j < 10){
                print.append(" ");
            }
            print.append(j).append(" : ");
            int cardNameLength = card.toString().length();
            if(cardNameLength == 2) {
                print.append(" ");
            }
            print.append(card).append(";   ");
            if (j % 10 == 0) {
                print.append("\r\n");
            }
        }
        print.delete(print.length() - 1, print.length());
        return print.toString();
    }
}
