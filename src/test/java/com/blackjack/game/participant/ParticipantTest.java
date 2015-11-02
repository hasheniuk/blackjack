package com.blackjack.game.participant;

import com.blackjack.util.cdi.qualifier.game.participant.Dealer;
import com.blackjack.util.cdi.qualifier.game.participant.Player;
import org.jglue.cdiunit.ActivatedAlternatives;
import org.jglue.cdiunit.CdiRunner;
import org.junit.*;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static com.blackjack.game.deck.Card.*;

@RunWith(CdiRunner.class)
public class ParticipantTest {

    @Inject @Player
    private PlayerState player;

    @Inject @Dealer
    private DealerState dealer;

    @Test
    public void testPlayerScoreAfterAddClubs3Card() {
        player.addCardToHand(C3);
        assertEquals(player.getScore(), 3);
    }

    @Test
    public void testDealerScoreAfterAddDiamonds4Card() {
        dealer.addCardToHand(D4);
        assertEquals(dealer.getScore(), 4);
    }

    @Test
    public void testPlayerScoreAfterAddSpadesAceCard() {
        player.addCardToHand(SA);
        assertEquals(player.getScore(), 11);
    }

    @Test
    public void testDealerScoreAfterAddHeartsAceCard() {
        dealer.addCardToHand(HA);
        assertEquals(dealer.getScore(), 11);
    }

    @Test
    public void testPlayerScoreAfterAddClubs5AndDiamondsJackCards() {
        player.addCardToHand(C5);
        player.addCardToHand(DJ);
        assertEquals(player.getScore(), 15);
    }

    @Test
    public void testDealerScoreAfterAddHearts7AndSpadesKingCards() {
        dealer.addCardToHand(H7);
        dealer.addCardToHand(SK);
        assertEquals(dealer.getScore(), 17);
    }

    @Test
    public void testDealerScoreAfterAddHearts7AndSpadesKingCardsThenHideSecondCard() {
        dealer.addCardToHand(H7);
        dealer.addCardToHand(SK);
        dealer.hideSecondCard();
        assertEquals(dealer.getScore(), 7);
    }

    @Test
    public void testPlayerScoreAfterAddClubsAceAndDiamondsAceCards() {
        player.addCardToHand(CA);
        player.addCardToHand(DA);
        assertEquals(player.getScore(), 12);
    }

    @Test
    public void testDealerScoreAfterAddHeartsAceAndSpadesAceCards() {
        dealer.addCardToHand(HA);
        dealer.addCardToHand(SA);
        assertEquals(dealer.getScore(), 12);
    }

    @Test
    public void testDealerScoreAfterAddHeartsAceAndSpadesAceCardsThenHideSecondCard() {
        dealer.addCardToHand(HA);
        dealer.addCardToHand(SA);
        dealer.hideSecondCard();
        assertEquals(dealer.getScore(), 11);
    }

    @Test
    public void testDealerScoreAfterAddHeartsAceAndSpadesAceCardsThenHideAndShowSecondCard() {
        dealer.addCardToHand(HA);
        dealer.addCardToHand(SA);
        dealer.hideSecondCard();
        dealer.showSecondCard();
        assertEquals(dealer.getScore(), 12);
    }

    @Test
    public void testPlayerScoreAfterAddClubs5AndHeartsAceAndDiamondsJackCards() {
        player.addCardToHand(C5);
        player.addCardToHand(HA);
        player.addCardToHand(DJ);
        assertEquals(player.getScore(), 16);
    }

    @Test
    public void testDealerScoreAfterAddHearts7AndSpadesAceAndSpadesKingCards() {
        dealer.addCardToHand(H7);
        dealer.addCardToHand(SA);
        dealer.addCardToHand(SK);
        assertEquals(dealer.getScore(), 18);
    }

    @Test
    public void testDealerScoreAfterAddHearts7AndSpadesAceThenHideAndShowSecondCardAndAddSpadesKingCards() {
        dealer.addCardToHand(H7);
        dealer.addCardToHand(SA);
        dealer.hideSecondCard();
        dealer.showSecondCard();
        dealer.addCardToHand(SK);
        assertEquals(dealer.getScore(), 18);
    }

    @Test
    public void testPlayerScoreAfterAddFourAces() {
        player.addCardToHand(HA);
        player.addCardToHand(DA);
        player.addCardToHand(SA);
        player.addCardToHand(CA);
        assertEquals(player.getScore(), 14);
    }

    @Test
    public void testDealerScoreAfterAddFourAces() {
        dealer.addCardToHand(HA);
        dealer.addCardToHand(DA);
        dealer.addCardToHand(SA);
        dealer.addCardToHand(CA);
        assertEquals(dealer.getScore(), 14);
    }

    @Test
    public void testDealerScoreAfterAddTwoAcesThenHideAndShowSecondCardAndAddTwoAces() {
        dealer.addCardToHand(HA);
        dealer.addCardToHand(DA);
        dealer.hideSecondCard();
        dealer.showSecondCard();
        dealer.addCardToHand(SA);
        dealer.addCardToHand(CA);
        assertEquals(dealer.getScore(), 14);
    }

    @Test
    public void testPlayerScoreAfterAddFourAcesAndFour2AndThree3() {
        player.addCardToHand(HA);
        player.addCardToHand(DA);
        player.addCardToHand(SA);
        player.addCardToHand(CA);
        player.addCardToHand(H2);
        player.addCardToHand(D2);
        player.addCardToHand(S2);
        player.addCardToHand(C2);
        player.addCardToHand(H3);
        player.addCardToHand(D3);
        player.addCardToHand(S3);
        assertEquals(player.getScore(), 21);
    }

    @Test
    public void testDealerScoreAfterAddClubs3AndDiamondsAceThenHideAndShowSecondCardAndAddDQandHJandSQ() {
        dealer.addCardToHand(C3);
        dealer.addCardToHand(DA);
        dealer.hideSecondCard();
        dealer.showSecondCard();
        dealer.addCardToHand(DQ);
        dealer.addCardToHand(HJ);
        dealer.addCardToHand(CQ);
        assertEquals(dealer.getScore(), 34);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnsupportedOperationExceptionHidePlayerSecondCard() {
        player.addCardToHand(CJ);
        player.addCardToHand(C9);
        player.hideSecondCard();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnsupportedOperationExceptionShowPlayerSecondCard() {
        player.addCardToHand(CJ);
        player.addCardToHand(C9);
        player.hideSecondCard();
        player.showSecondCard();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnsupportedOperationExceptionHideDealerSecondCardAfterFirstCardAdded() {
        player.addCardToHand(CJ);
        player.hideSecondCard();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnsupportedOperationExceptionHideDealerSecondCardAfterThirdCardAdded() {
        player.addCardToHand(CJ);
        player.addCardToHand(C3);
        player.addCardToHand(S10);
        player.hideSecondCard();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testUnsupportedOperationExceptionHideDealerShowSecondCardWhenCardWasNotHided() {
        player.addCardToHand(CJ);
        player.addCardToHand(C10);
        player.showSecondCard();
    }
}