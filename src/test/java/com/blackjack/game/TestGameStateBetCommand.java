package com.blackjack.game;

import com.blackjack.game.deck.Card;
import com.blackjack.game.participant.GameResult;
import org.jglue.cdiunit.CdiRunner;
import org.junit.*;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static com.blackjack.game.participant.GameResult.*;
import static com.blackjack.game.deck.Card.*;

@RunWith(CdiRunner.class)
public class TestGameStateBetCommand extends AbstractTestGameState {

    @Inject
    private GameState blackjack;

    @Inject
    private GameState blackjackPush;

    @Inject
    private GameState gameContinue;

    @Before
    public void before() {
        initGameState(blackjack, BLACK_JACK_SEED);
        blackjack.executeAndPersist(bet);

        initGameState(blackjackPush, BLACK_JACK_PUSH_SEED);
        blackjackPush.executeAndPersist(bet);

        initGameState(gameContinue, FIVE_HITS_CONTINUE_SEED);
        gameContinue.executeAndPersist(bet);
    }

    // BLACKJACK TESTS
    @Test
    public void testPlayerGameResultBlackJack() {
        GameResult playerResults = blackjack.getPlayer().getGameResult();
        assertEquals(BLACKJACK, playerResults);
    }

    @Test
    public void testDealerGameResultBlackJack() {
        GameResult dealerResults = blackjack.getDealer().getGameResult();
        assertEquals(LOSE, dealerResults);
    }

    @Test
    public void testPlayerCardsBlackJack() {
        List<Card> expectedCards = Arrays.asList(CA, D10);
        List<Card> playerCards = blackjack.getPlayer().getHand();
        assertEquals(expectedCards, playerCards);
    }

    @Test
    public void testDealerCardsBlackJack() {
        List<Card> expectedCards = Arrays.asList(CK, C6);
        List<Card> dealerCards = blackjack.getDealer().getHand();
        assertEquals(expectedCards, dealerCards);
    }

    @Test
    public void testPlayerScoreBlackJack() {
        int expectedScore = 21;
        int actualScore = blackjack.getPlayer().getScore();
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testDealerScoreBlackJack() {
        int expectedScore = 16;
        int actualScore = blackjack.getDealer().getScore();
        assertEquals(expectedScore, actualScore);
    }

    // BLACKJACK PUSH TESTS
    @Test
    public void testPlayerGameResultBlackJackPush() {
        GameResult playerResults = blackjackPush.getPlayer().getGameResult();
        assertEquals(PUSH, playerResults);
    }

    @Test
    public void testDealerGameResultBlackJackPush() {
        GameResult dealerResults = blackjackPush.getDealer().getGameResult();
        assertEquals(PUSH, dealerResults);
    }

    @Test
    public void testPlayerCardsBlackJackPush() {
        List<Card> expectedCards = Arrays.asList(CK, CA);
        List<Card> playerCards = blackjackPush.getPlayer().getHand();
        assertEquals(expectedCards, playerCards);
    }

    @Test
    public void testDealerCardsBlackJackPush() {
        List<Card> expectedCards = Arrays.asList(SA, C10);
        List<Card> dealerCards = blackjackPush.getDealer().getHand();
        assertEquals(expectedCards, dealerCards);
    }

    @Test
    public void testPlayerScoreBlackJackPush() {
        int expectedScore = 21;
        int actualScore = blackjackPush.getPlayer().getScore();
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testDealerScoreBlackJackPush() {
        int expectedScore = 21;
        int actualScore = blackjackPush.getDealer().getScore();
        assertEquals(expectedScore, actualScore);
    }

    // CONTINUE TESTS
    @Test
    public void testPlayerGameResultContinue() {
        GameResult playerResults = gameContinue.getPlayer().getGameResult();
        assertEquals(CONTINUE, playerResults);
    }

    @Test
    public void testDealerGameResultContinue() {
        GameResult dealerResults = gameContinue.getDealer().getGameResult();
        assertEquals(CONTINUE, dealerResults);
    }

    @Test
    public void testPlayerCardsContinue() {
        List<Card> expectedCards = Arrays.asList(HA, D5);
        List<Card> playerCards = gameContinue.getPlayer().getHand();
        assertEquals(expectedCards, playerCards);
    }

    @Test
    public void testDealerCardsContinue() {
        List<Card> expectedCards = Arrays.asList(H4, SHIRT);
        List<Card> dealerCards = gameContinue.getDealer().getHand();
        assertEquals(expectedCards, dealerCards);
    }

    @Test
    public void testPlayerScoreContinue() {
        int expectedScore = 16;
        int actualScore = gameContinue.getPlayer().getScore();
        assertEquals(expectedScore, actualScore);
    }

    @Test
    public void testDealerScoreContinue() {
        int expectedScore = 4;
        int actualScore = gameContinue.getDealer().getScore();
        assertEquals(expectedScore, actualScore);
    }
}
