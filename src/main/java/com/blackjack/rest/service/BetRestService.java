package com.blackjack.rest.service;

import com.blackjack.entity.*;
import com.blackjack.game.GameState;
import com.blackjack.game.command.Command;
import com.blackjack.util.cdi.qualifier.game.command.Bet;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.servlet.http.*;

import static com.blackjack.servlet.constant.SessionAttribute.*;

@Path("/bet")
@RequestScoped
public class BetRestService {

    @Context
    private HttpServletRequest req;

    @Inject
    private GameState gameState;

    @Inject @Bet
    private Command betCommand;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response bet(String betValue) {
        try {
            int betValueInt = Integer.parseInt(betValue);
            HttpSession session = req.getSession();
            PlayerEntity playerEntity = (PlayerEntity) session.getAttribute(PLAYER);
            gameState.setBet(betValueInt);
            gameState.setPlayerEntity(playerEntity);
            gameState.executeAndPersist(betCommand);
            session.setAttribute(GAME, gameState);
            return Response.ok(gameState, MediaType.APPLICATION_JSON).build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
