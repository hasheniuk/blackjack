package com.blackjack.game;

import com.blackjack.dao.*;
import com.blackjack.entity.*;
import com.blackjack.game.command.*;
import com.blackjack.game.deck.DeckOfCards;
import com.blackjack.game.participant.*;
import com.blackjack.util.cdi.qualifier.game.command.*;
import com.blackjack.util.cdi.qualifier.game.participant.*;
import org.jglue.cdiunit.CdiRunner;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.lang.reflect.Field;

import static org.mockito.Mockito.mock;

@RunWith(CdiRunner.class)
public class AbstractTestGameState {
    protected static final long BLACK_JACK_SEED = 23;
    protected static final long BLACK_JACK_PUSH_SEED = 315;
    protected static final long FIVE_HITS_CONTINUE_SEED = 521;

    @Inject @Player
    protected PlayerState playerState;

    @Inject @Dealer
    protected DealerState dealerState;

    @Inject @Bet
    protected BetCommand bet;

    @Inject @Hit
    protected HitCommand hit;

    @Inject @Stand
    protected StandCommand stand;

    protected void initGameState(GameState gameState, long deckSeed) {
        PlayerEntity playerEntity = new PlayerEntity("test");
        playerEntity.setWalletId(0);
        playerEntity.setMoney(1000);

        int betValue = 10;

        gameState.setPlayerEntity(playerEntity);
        gameState.setBet(betValue);

        Class gameStateClass = gameState.getClass();
        try {
            Field deckOfCardsField = gameStateClass.getDeclaredField("deckOfCards");
            deckOfCardsField.setAccessible(true);
            deckOfCardsField.set(gameState, DeckOfCards.shuffle(deckSeed));
        } catch (Exception e) {
            e.printStackTrace();
        }

        PlayerDao playerDaoMock = mock(PlayerDao.class);
        GameDao gameDaoMock = mock(GameDao.class);

        Class betClass = bet.getClass();
        Class hitClass = hit.getClass();
        Class standClass = stand.getClass();
        try {
            Field betPlayerDaoField = betClass.getDeclaredField("playerDao");
            betPlayerDaoField.setAccessible(true);
            betPlayerDaoField.set(bet, playerDaoMock);

            Field betGameDaoField = betClass.getDeclaredField("gameDao");
            betGameDaoField.setAccessible(true);
            betGameDaoField.set(bet, gameDaoMock);

            Field hitPlayerDaoField = hitClass.getDeclaredField("playerDao");
            hitPlayerDaoField.setAccessible(true);
            hitPlayerDaoField.set(hit, playerDaoMock);

            Field hitGameDaoField = hitClass.getDeclaredField("gameDao");
            hitGameDaoField.setAccessible(true);
            hitGameDaoField.set(hit, gameDaoMock);

            Field standPlayerDaoField = standClass.getDeclaredField("playerDao");
            standPlayerDaoField.setAccessible(true);
            standPlayerDaoField.set(stand, playerDaoMock);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
