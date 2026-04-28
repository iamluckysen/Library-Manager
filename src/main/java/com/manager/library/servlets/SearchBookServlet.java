package com.manager.library.servlets;

import com.manager.library.entities.Book;
import com.manager.library.service.LibrayServiceWeb;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/searchBook")
public class SearchBookServlet extends HttpServlet {
    private LibrayServiceWeb libraryService = new LibrayServiceWeb();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session==null || session.getAttribute("studentID") == null){
            resp.sendRedirect("index.jsp");
            return;
        }
        String sBookId = req.getParameter("bookId");

        if(!sBookId.trim().isEmpty() && sBookId!=null){
            try{
                int bookId = Integer.parseInt(sBookId);
                Book book = libraryService.getBookById(bookId);
                if(book!=null){
                    req.setAttribute("foundBook",book);
                }else {
                    req.setAttribute("errMsg", "No book found for this id!");
                }
            }catch (NumberFormatException n)
            {
                req.setAttribute("errMsg", "Invalid ID format, Enter Number Only");
            }
        }
        req.getRequestDispatcher("searchbook.jsp").forward(req,resp);

    }
}
