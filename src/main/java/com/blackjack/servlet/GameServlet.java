package com.blackjack.servlet;

import com.blackjack.entity.PlayerEntity;

import javax.enterprise.context.RequestScoped;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

import static com.blackjack.servlet.constant.Page.*;
import static com.blackjack.servlet.constant.SessionAttribute.*;

@RequestScoped
@WebServlet(urlPatterns = "/blackjack", asyncSupported = true)
public class GameServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PlayerEntity playerEntity = (PlayerEntity) session.getAttribute(PLAYER);
        if (playerEntity != null) {
            ServletContext context = getServletContext();
            RequestDispatcher dispatcher = context.getRequestDispatcher(BLACKJACK);
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("");
        }
    }
}
