package com.blackjack.dao;

import com.blackjack.entity.GameHistoryEntity;

public class GameDao extends AbstractDao {

    public void saveGameHistory(GameHistoryEntity gameHistoryEntity) {
        beginTransaction();
        try {
            entityManager.persist(gameHistoryEntity);
            entityManager.flush();
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }

    public void incrementHitsCount(GameHistoryEntity gameHistoryEntity) {
        beginTransaction();
        try {
            int hitsCount = gameHistoryEntity.getHitsCount();
            hitsCount++;
            gameHistoryEntity.setHitsCount(hitsCount);
            entityManager.merge(gameHistoryEntity);
            entityManager.flush();
            commitTransaction();
        } catch (Exception e) {
            rollbackTransaction();
            e.printStackTrace();
        }
    }
}
