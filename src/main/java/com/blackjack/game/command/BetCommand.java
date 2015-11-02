package com.blackjack.game.command;

import com.blackjack.dao.*;
import com.blackjack.util.cdi.qualifier.game.command.Bet;

import javax.inject.Inject;

import static com.blackjack.game.participant.GameResult.*;

@Bet
public class BetCommand extends Command {

    @Inject
    protected PlayerDao playerDao;

    @Inject
    protected GameDao gameDao;

    @Override
    public void execute() {
        for (int i = 0; i < 2; i++) {
            player.addCardToHand(deckOfCards.getNextCard());
            dealer.addCardToHand(deckOfCards.getNextCard());
        }

        int playerTotal = player.getScore();
        int dealerTotal = dealer.getScore();

        if (playerTotal == 21) {
            if (playerTotal > dealerTotal) {
                player.setGameResult(BLACKJACK);
                dealer.setGameResult(LOSE);
            } else {
                player.setGameResult(PUSH);
                dealer.setGameResult(PUSH);
            }
        } else {
            dealer.hideSecondCard();
            player.setGameResult(CONTINUE);
            dealer.setGameResult(CONTINUE);
        }
    }

    @Override
    public void persist() {
        gameDao.saveGameHistory(gameHistory);
        switch (player.getGameResult()) {
            case BLACKJACK: {
                playerDao.changeMoney(playerEntity, (int) (bet * 1.5));
                break;
            } case PUSH: {
                break;
            } default: {
                playerDao.changeMoney(playerEntity, -bet);
            }
        }
    }
}
