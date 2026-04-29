package com.manager.library.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req , HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession(false);
        if(session!=null){
            session.invalidate();
            resp.sendRedirect("index.jsp");
        }


    }
}
