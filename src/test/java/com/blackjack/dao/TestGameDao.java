package com.blackjack.dao;

import com.blackjack.entity.*;
import com.blackjack.game.deck.DeckOfCards;
import org.jglue.cdiunit.*;
import org.junit.*;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(CdiRunner.class)
public class TestGameDao extends AbstractTestDao {

    @Inject
    private GameDao gameDao;

    private PlayerEntity playerEntity;
    private GameHistoryEntity gameHistoryEntity;

    @Before
    public void before() {
        playerEntity = new PlayerEntity("test");
        playerEntity.setMoney(1000);
        try {
            beginTransaction();
            entityManager.persist(playerEntity);
            entityManager.flush();
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
        gameHistoryEntity = new GameHistoryEntity(DeckOfCards.shuffle(1234567890), LocalDateTime.now());
        gameHistoryEntity.setPlayer(playerEntity);
        gameHistoryEntity.setBet(10);
        gameHistoryEntity.setStartDate(LocalDateTime.now());
    }

    @After
    public void after() {
        try {
            beginTransaction();
            entityManager.remove(playerEntity);
            entityManager.flush();
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }

    @Test @InRequestScope
    public void testSaveGameHistory() {
        gameDao.saveGameHistory(gameHistoryEntity);
        long gameId = gameHistoryEntity.getGameId();
        GameHistoryEntity foundedGameHistoryEntity = entityManager.find(GameHistoryEntity.class, gameId);
        assertEquals(gameHistoryEntity, foundedGameHistoryEntity);
    }

    @Test @InRequestScope
    public void testOneIncrementHitsCount() {
        gameDao.saveGameHistory(gameHistoryEntity);
        long gameId = gameHistoryEntity.getGameId();
        gameDao.incrementHitsCount(gameHistoryEntity);
        GameHistoryEntity foundedGameHistoryEntity = entityManager.find(GameHistoryEntity.class, gameId);
        assertEquals(1, foundedGameHistoryEntity.getHitsCount());
    }

    @Test @InRequestScope
    public void testTwoIncrementsHitsCount() {
        gameDao.saveGameHistory(gameHistoryEntity);
        long gameId = gameHistoryEntity.getGameId();
        gameDao.incrementHitsCount(gameHistoryEntity);
        gameDao.incrementHitsCount(gameHistoryEntity);
        GameHistoryEntity foundedGameHistoryEntity = entityManager.find(GameHistoryEntity.class, gameId);
        assertEquals(2, foundedGameHistoryEntity.getHitsCount());
    }

    @Test @InRequestScope
    public void testThreeIncrementsHitsCount() {
        gameDao.saveGameHistory(gameHistoryEntity);
        long gameId = gameHistoryEntity.getGameId();
        gameDao.incrementHitsCount(gameHistoryEntity);
        gameDao.incrementHitsCount(gameHistoryEntity);
        gameDao.incrementHitsCount(gameHistoryEntity);
        GameHistoryEntity foundedGameHistoryEntity = entityManager.find(GameHistoryEntity.class, gameId);
        assertEquals(3, foundedGameHistoryEntity.getHitsCount());
    }
}
