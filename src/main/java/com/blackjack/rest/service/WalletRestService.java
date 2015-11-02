package com.blackjack.rest.service;

import com.blackjack.entity.PlayerEntity;
import com.blackjack.dao.PlayerDao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.servlet.http.*;

import static com.blackjack.servlet.constant.SessionAttribute.*;

@Path("/wallet")
@RequestScoped
public class WalletRestService {

    @Context
    private HttpServletRequest req;

    @Inject
    private PlayerDao playerDao;

    @Inject
    private PlayerEntity playerEntity;

    @GET
    @Path("/money")
    public Response getMoney() {
        HttpSession session = req.getSession();
        PlayerEntity playerEntity = (PlayerEntity) session.getAttribute(PLAYER);
        String money = String.valueOf(playerEntity.getMoney());
        return Response.ok(money).build();
    }

    @POST
    @Path("/fillup")
    public Response fillUpMoney(String fillUp) {
        try {
            int value = Integer.parseInt(fillUp);
            HttpSession session = req.getSession();
            PlayerEntity playerEntity = (PlayerEntity) session.getAttribute(PLAYER);
            playerDao.fillUpWallet(playerEntity, value);
            session.setAttribute(PLAYER, playerEntity);
            String money = String.valueOf(playerEntity.getMoney());
            return Response.ok(money).build();
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
