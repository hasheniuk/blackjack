package com.blackjack.game.command;

import com.blackjack.dao.*;
import com.blackjack.game.deck.Card;
import com.blackjack.util.cdi.qualifier.game.command.Stand;

import javax.inject.Inject;

import static com.blackjack.game.participant.GameResult.*;

@Stand
public class StandCommand extends Command {

    @Inject
    protected PlayerDao playerDao;

    @Override
    public void execute() {
        if (player.getGameResult() != CONTINUE) {
            throw new UnsupportedOperationException("Game overs before stand faze. " +
                                                    "Player game result : " + player.getGameResult());
        }
        dealer.showSecondCard();
        while (dealer.getScore() < 17) {
            Card card = deckOfCards.getNextCard();
            dealer.addCardToHand(card);
        }

        int playerTotal = player.getScore();
        int dealerTotal = dealer.getScore();

        if (dealerTotal > 21) {
            player.setGameResult(WIN);
            dealer.setGameResult(BUST);
        } else if (dealerTotal == playerTotal) {
            player.setGameResult(PUSH);
            dealer.setGameResult(PUSH);
        } else if (dealerTotal > playerTotal) {
            player.setGameResult(LOSE);
            dealer.setGameResult(WIN);
        } else {
            player.setGameResult(WIN);
            dealer.setGameResult(LOSE);
        }
    }

    @Override
    public void persist() {
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
