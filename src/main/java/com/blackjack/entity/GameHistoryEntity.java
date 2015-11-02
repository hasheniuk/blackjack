package com.blackjack.entity;

import com.blackjack.game.deck.DeckOfCards;
import com.blackjack.util.percistence.converter.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "game_history")
public class GameHistoryEntity {

    @Id
    @SequenceGenerator(name="game_history_game_id_seq", sequenceName="game_history_game_id_seq", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="game_history_game_id_seq")
    @Column(name = "game_id")
    private long gameId;

    @Convert(converter = LocalDateTimeConverter.class)
    @Column(name = "start_date")
    private LocalDateTime startDate;

    @ManyToOne
    @JoinColumn(name = "player")
    private PlayerEntity player;

    @Convert(converter = DeckOfCardsConverter.class)
    @Column(name = "deck_seed")
    private DeckOfCards deckOfCards;

    @Column(name = "bet")
    private int bet;

    @Column(name = "hits_count")
    private int hitsCount;

    public GameHistoryEntity() {}

    public GameHistoryEntity(DeckOfCards deckOfCards, LocalDateTime startDate) {
        this.deckOfCards = deckOfCards;
        this.startDate = startDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameHistoryEntity)) return false;

        GameHistoryEntity that = (GameHistoryEntity) o;

        if (gameId != that.gameId) return false;
        if (bet != that.bet) return false;
        if (hitsCount != that.hitsCount) return false;
        if (!startDate.equals(that.startDate)) return false;
        if (!player.equals(that.player)) return false;
        return deckOfCards.equals(that.deckOfCards);

    }

    @Override
    public int hashCode() {
        int result = (int) (gameId ^ (gameId >>> 32));
        result = 31 * result + startDate.hashCode();
        result = 31 * result + player.hashCode();
        result = 31 * result + deckOfCards.hashCode();
        result = 31 * result + bet;
        result = 31 * result + hitsCount;
        return result;
    }

    public long getGameId() {
        return gameId;
    }

    public void setGameId(long gameId) {
        this.gameId = gameId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public DeckOfCards getDeckOfCards() {
        return deckOfCards;
    }

    public void setDeckOfCards(DeckOfCards deckOfCards) {
        this.deckOfCards = deckOfCards;
    }

    public int getBet() {
        return bet;
    }

    public void setBet(int bet) {
        this.bet = bet;
    }

    public int getHitsCount() {
        return hitsCount;
    }

    public void setHitsCount(int hitsCount) {
        this.hitsCount = hitsCount;
    }

    @Override
    public String toString() {
        return "GameHistoryEntity{" +
                "gameId=" + gameId +
                ", startDate=" + startDate +
                ", player=" + player +
                ", deckOfCardsSeed=" + deckOfCards.getSeed() +
                ", bet=" + bet +
                ", hitsCount=" + hitsCount +
                '}';
    }
}
