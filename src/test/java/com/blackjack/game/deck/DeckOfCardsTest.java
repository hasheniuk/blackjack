package com.blackjack.game.deck;

import org.junit.*;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class DeckOfCardsTest {
    private static final int DECK_SIZE = 52;

    private long seed;
    private DeckOfCards deckOfCards;

    @Before
    public void before() {
        seed = System.nanoTime();
        deckOfCards = DeckOfCards.shuffle(seed);
    }

    @Test
    public void testEqualsTwoDecksWithOneSeed() {
        boolean isEquals = true;
        DeckOfCards secondDeck = DeckOfCards.shuffle(seed);
        for (int i = 0; i < DECK_SIZE; i++) {
            Card first = deckOfCards.getNextCard();
            Card second = secondDeck.getNextCard();
            if (first != second) {
                isEquals = false;
                break;
            }
        }
        assertEquals(isEquals, true);
    }

    @Test(expected = NoSuchElementException.class)
    public void testNoSuchElementExceptionWhenGettingCardFromEmptyDeck() {
        for (int i = 0; i < DECK_SIZE; i++) {
            deckOfCards.getNextCard();
        }
        deckOfCards.getNextCard();
    }

    @Test
    public void testUniqueCardsInDeck() {
        boolean isUnique = true;
        List<Card> uniqueCards = new ArrayList<>(DECK_SIZE);
        for (int i = 0; i < DECK_SIZE; i++) {
            Card card = deckOfCards.getNextCard();
            if (uniqueCards.contains(card)) {
                isUnique = false;
                break;
            }
            uniqueCards.add(card);
        }
        assertEquals(isUnique, true);
    }

    @Test
    public void testNoCardShirtInDeck() {
        boolean hasShirt = false;
        for (int i = 0; i < DECK_SIZE; i++) {
            Card card = deckOfCards.getNextCard();
            if (card == Card.SHIRT) {
                hasShirt = true;
                break;
            }
        }
        assertEquals(hasShirt, false);
    }
}
