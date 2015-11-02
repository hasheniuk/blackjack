package com.blackjack.game.participant;

import com.blackjack.game.deck.Card;
import com.blackjack.util.cdi.qualifier.game.participant.Dealer;

@Dealer
public class DealerState extends ParticipantState {
    private Card hiddenSecondCard;

    @Override
    public void hideSecondCard() {
        if (hand.size() == 2) {
            hiddenSecondCard = hand.get(1);
            if (hiddenSecondCard.isAce()) {
                aceScoreElevenCounter--;
            }
            score = hand.get(0).getValue();
            hand.set(1, Card.SHIRT);
        }
    }

    @Override
    public void showSecondCard() {
        if (hand.size() == 2 && hand.get(1) == Card.SHIRT) {
            hand.remove(1);
            addCardToHand(hiddenSecondCard);
        }
    }

}
