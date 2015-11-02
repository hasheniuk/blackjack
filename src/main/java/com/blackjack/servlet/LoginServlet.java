package com.blackjack.servlet;

import com.blackjack.entity.PlayerEntity;
import com.blackjack.dao.PlayerDao;

import javax.enterprise.context.RequestScoped;
import javax.servlet.annotation.WebServlet;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import static com.blackjack.servlet.constant.Page.*;
import static com.blackjack.servlet.constant.SessionAttribute.*;

@RequestScoped
@WebServlet(urlPatterns = "", asyncSupported = true)
public class LoginServlet extends HttpServlet {

    @Inject
    private PlayerDao playerDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(LOGIN);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String walletId = req.getParameter("wallet-number");
            long id = Long.parseLong(walletId);
            PlayerEntity playerEntity = null;
            if (id > 0) {
                playerEntity = playerDao.findPlayerByWalletId(id);
            }
            if (playerEntity != null) {
                HttpSession session = req.getSession(true);
                session.setAttribute(PLAYER, playerEntity);
                resp.sendRedirect("/blackjack");
            } else {
                redirectToLoginPage(req, resp);
            }
        } catch (NumberFormatException e) {
            redirectToLoginPage(req, resp);
        }
    }

    private void redirectToLoginPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("showWarning", true);
        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher(LOGIN);
        dispatcher.forward(req, resp);
    }
}
