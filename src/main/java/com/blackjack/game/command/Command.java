package com.blackjack.game.command;

import com.blackjack.entity.*;
import com.blackjack.game.GameState;
import com.blackjack.game.deck.DeckOfCards;
import com.blackjack.game.participant.*;

public abstract class Command {
    protected DeckOfCards deckOfCards;
    protected ParticipantState player;
    protected ParticipantState dealer;
    protected PlayerEntity playerEntity;
    protected int bet;
    protected GameHistoryEntity gameHistory;

    public void setGameState(GameState gameState) {
        this.deckOfCards = gameState.getDeckOfCards();
        this.player = gameState.getPlayer();
        this.dealer = gameState.getDealer();
        this.gameHistory = gameState.getGameHistory();
        this.playerEntity = gameHistory.getPlayer();
        this.bet = gameHistory.getBet();
    }

    public abstract void execute();
    public abstract void persist();
}
