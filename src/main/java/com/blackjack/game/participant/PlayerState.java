package com.blackjack.game.participant;

import com.blackjack.util.cdi.qualifier.game.participant.Player;

@Player
public class PlayerState extends ParticipantState {

    @Override
    public void hideSecondCard() {
        throw new UnsupportedOperationException("Operation hide second card is unsupported.");
    }

    @Override
    public void showSecondCard() {
        throw new UnsupportedOperationException("Operation show second card is unsupported.");
    }
}
