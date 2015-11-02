package com.blackjack.rest.service;

import com.blackjack.game.GameState;
import com.blackjack.game.command.Command;
import com.blackjack.util.cdi.qualifier.game.command.Stand;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import static com.blackjack.servlet.constant.SessionAttribute.*;

@Path("/stand")
@RequestScoped
public class StandRestService {

    @Context
    private HttpServletRequest req;

    @Inject @Stand
    private Command standCommand;

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response stand() {
        HttpSession session = req.getSession();
        GameState gameState = (GameState) session.getAttribute(GAME);
        gameState.executeAndPersist(standCommand);
        return Response.ok(gameState, MediaType.APPLICATION_JSON).build();
    }
}
