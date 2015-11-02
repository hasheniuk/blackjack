package com.blackjack.dao;

import com.blackjack.entity.*;

import java.time.LocalDateTime;

public class PlayerDao extends AbstractDao {

    public PlayerEntity findPlayerByWalletId(long walletId) {
        return entityManager.find(PlayerEntity.class, walletId);
    }

    public MoneyIncomeEntity fillUpWallet(PlayerEntity playerEntity, int fillUp) {
        MoneyIncomeEntity moneyIncomeEntity = null;
        try {
            beginTransaction();
            int currentMoney = playerEntity.getMoney();
            playerEntity.setMoney(currentMoney + fillUp);
            entityManager.merge(playerEntity);
            moneyIncomeEntity = new MoneyIncomeEntity(LocalDateTime.now(), playerEntity, fillUp);
            entityManager.persist(moneyIncomeEntity);
            entityManager.flush();
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
        return moneyIncomeEntity;
    }

    public void changeMoney(PlayerEntity playerEntity, int value) {
        try {
            beginTransaction();
            int currentMoney = playerEntity.getMoney();
            playerEntity.setMoney(currentMoney + value);
            entityManager.merge(playerEntity);
            entityManager.flush();
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }
}
