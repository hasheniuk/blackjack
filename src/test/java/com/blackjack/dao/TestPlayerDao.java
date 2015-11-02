package com.blackjack.dao;

import com.blackjack.entity.*;
import org.jglue.cdiunit.*;
import org.junit.*;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(CdiRunner.class)
public class TestPlayerDao extends AbstractTestDao {
    private static final int FILL_UP_MONEY = 500;
    private static final int BET = 10;

    @Inject
    private PlayerDao playerDao;

    private PlayerEntity playerEntity;

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
    public void testFindPlayerByWalletId() {
        long walletId = playerEntity.getWalletId();
        PlayerEntity foundedEntity = playerDao.findPlayerByWalletId(walletId);
        assertEquals(playerEntity, foundedEntity);
    }

    @Test @InRequestScope
    public void testFillUpWalletChangedPlayerMoney() {
        long walletId = playerEntity.getWalletId();
        playerDao.changeMoney(playerEntity, FILL_UP_MONEY);
        PlayerEntity changedPlayer = playerDao.findPlayerByWalletId(walletId);
        assertEquals(1500, changedPlayer.getMoney());
    }

    @Test @InRequestScope
    public void testFillUpWalletCreatedMoneyIncome() {
        playerDao.changeMoney(playerEntity, FILL_UP_MONEY);
        MoneyIncomeEntity expectedMoneyIncome = playerDao.fillUpWallet(playerEntity, FILL_UP_MONEY);
        long operationId = expectedMoneyIncome.getOperationId();
        MoneyIncomeEntity actualMoneyIncome = entityManager.find(MoneyIncomeEntity.class, operationId);
        assertEquals(expectedMoneyIncome, actualMoneyIncome);
    }

    @Test @InRequestScope
    public void testChangeMoney() {
        long walletId = playerEntity.getWalletId();
        playerDao.changeMoney(playerEntity, -BET);
        PlayerEntity changedPlayer = playerDao.findPlayerByWalletId(walletId);
        assertEquals(990, changedPlayer.getMoney());
    }
}
