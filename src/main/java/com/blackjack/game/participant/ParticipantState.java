package com.blackjack.game.participant;

import com.blackjack.game.deck.Card;

import javax.xml.bind.annotation.*;
import java.util.*;

public abstract class ParticipantState {

    @XmlElement
    protected List<Card> hand = new ArrayList<>();

    @XmlElement
    protected int score;

    protected GameResult gameResult;
    protected int aceScoreElevenCounter;

    public void addCardToHand(Card card) {
        if (card.isAce()) {
            aceScoreElevenCounter++;
        }
        hand.add(card);
        score += card.getValue();
        while (score > 21 && aceScoreElevenCounter > 0) {
            score -= 10;
            aceScoreElevenCounter--;
        }
    }

    public List<Card> getHand() {
        return hand;
    }

    public GameResult getGameResult() {
        return gameResult;
    }

    public void setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
    }

    public int getScore() {
        return score;
    }

    public abstract void hideSecondCard();
    public abstract void showSecondCard();
}
