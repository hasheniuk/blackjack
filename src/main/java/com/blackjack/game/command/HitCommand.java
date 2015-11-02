package com.blackjack.game.command;

import com.blackjack.dao.*;
import com.blackjack.game.deck.Card;
import com.blackjack.util.cdi.qualifier.game.command.Hit;

import javax.inject.Inject;

import static com.blackjack.game.participant.GameResult.*;

@Hit
public class HitCommand extends Command {

    @Inject
    private PlayerDao playerDao;

    @Inject
    private GameDao gameDao;

    @Override
    public void execute() {
        Card card = deckOfCards.getNextCard();
        player.addCardToHand(card);
        if (player.getScore() > 21) {
            dealer.showSecondCard();
            player.setGameResult(BUST);
            dealer.setGameResult(WIN);
        } else if (player.getScore() == 21) {
            dealer.showSecondCard();

            while (dealer.getScore() < 17) {
                card = deckOfCards.getNextCard();
                dealer.addCardToHand(card);
            }

            int dealerTotal = dealer.getScore();

            if (dealerTotal > 21) {
                player.setGameResult(WIN);
                dealer.setGameResult(BUST);
            } else if (dealerTotal < 21) {
                player.setGameResult(WIN);
                dealer.setGameResult(LOSE);
            } else {
                player.setGameResult(PUSH);
                dealer.setGameResult(PUSH);
            }
        }
    }

    @Override
    public void persist() {
        gameDao.incrementHitsCount(gameHistory);
        switch (player.getGameResult()) {
            case WIN: {
                playerDao.changeMoney(playerEntity, bet * 2);
                break;
            } case PUSH: {
                playerDao.changeMoney(playerEntity, bet);
                break;
            }
        }
    }
}
