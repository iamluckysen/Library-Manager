package com.manager.library.servlets;

import com.manager.library.entities.Book;
import com.manager.library.entities.MyBooks;
import com.manager.library.service.LibrayServiceWeb;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet("/myBookList")
public class MyBookServlet extends HttpServlet {
    LibrayServiceWeb service = new LibrayServiceWeb();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        HttpSession session = req.getSession(false);

        if(session!=null && session.getAttribute("studentID")!=null) {
            int stdId = (Integer) session.getAttribute("studentID");
            try {
                List<MyBooks> myBooks= service.getMyBookList(stdId);
                req.setAttribute("myBooks", myBooks);
                req.getRequestDispatcher("myBooklist.jsp").forward(req, resp);
            } catch (Exception e) {
                System.out.println("error in fetching book");
            }
        }else {
            resp.sendRedirect("index.jsp");
        }


    }
}
