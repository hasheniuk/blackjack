package com.blackjack.game;

import com.blackjack.entity.*;
import com.blackjack.game.command.*;
import com.blackjack.game.deck.*;
import com.blackjack.game.participant.*;
import com.blackjack.util.cdi.qualifier.game.participant.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.xml.bind.annotation.*;
import java.time.LocalDateTime;

@XmlRootElement
public class GameState {
    private DeckOfCards deckOfCards;
    private GameHistoryEntity gameHistory;

    @Inject @Player
    @XmlElement
    private ParticipantState player;

    @Inject @Dealer
    @XmlElement
    private ParticipantState dealer;

    @PostConstruct
    public void postConstruct() {
        long deckSeed = System.nanoTime();
        deckOfCards = DeckOfCards.shuffle(deckSeed);
        gameHistory = new GameHistoryEntity(deckOfCards, LocalDateTime.now());
    }

    public void setPlayerEntity(PlayerEntity playerEntity) {
        this.gameHistory.setPlayer(playerEntity);
    }

    public void setBet(int bet) {
        this.gameHistory.setBet(bet);
    }

    public void executeAndPersist(Command command) {
        command.setGameState(this);
        execute(command);
        persist(command);
    }

    private void execute(Command command) {
        command.execute();
    }

    private void persist(Command command) {
        command.persist();
    }

    public GameHistoryEntity getGameHistory() {
        return gameHistory;
    }

    public DeckOfCards getDeckOfCards() {
        return deckOfCards;
    }

    public ParticipantState getPlayer() {
        return player;
    }

    public ParticipantState getDealer() {
        return dealer;
    }
}