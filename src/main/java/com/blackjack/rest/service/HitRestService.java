package com.blackjack.rest.service;

import com.blackjack.game.GameState;
import com.blackjack.game.command.Command;
import com.blackjack.util.cdi.qualifier.game.command.Hit;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import static com.blackjack.servlet.constant.SessionAttribute.*;

@Path("/hit")
@RequestScoped
public class HitRestService {

    @Context
    private HttpServletRequest req;

    @Inject @Hit
    private Command hitCommand;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response hit() {
        HttpSession session = req.getSession();
        GameState gameState = (GameState) session.getAttribute(GAME);
        gameState.executeAndPersist(hitCommand);
        session.setAttribute(GAME, gameState);
        return Response.ok(gameState, MediaType.APPLICATION_JSON).build();
    }
}
