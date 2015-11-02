package com.blackjack.util.percistence.converter;

import com.blackjack.game.deck.DeckOfCards;

import javax.persistence.*;

@Converter
public class DeckOfCardsConverter implements AttributeConverter<DeckOfCards, Long> {

    @Override
    public Long convertToDatabaseColumn(DeckOfCards deckOfCards) {
        return deckOfCards.getSeed();
    }

    @Override
    public DeckOfCards convertToEntityAttribute(Long seed) {
        return DeckOfCards.shuffle(seed);
    }
}
