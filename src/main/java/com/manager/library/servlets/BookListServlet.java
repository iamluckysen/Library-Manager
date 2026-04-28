package com.manager.library.servlets;

import com.manager.library.entities.Book;
import com.manager.library.service.LibrayServiceWeb;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/bookList")
public class BookListServlet extends HttpServlet {
    LibrayServiceWeb service = new LibrayServiceWeb();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
       try{
           List<Book> bookList = service.getbooklist();
           req.setAttribute("bookList", bookList);
           req.getRequestDispatcher("booklist.jsp").forward(req, resp);
       } catch (Exception e) {
           System.out.println("error in fetching book");
       }


    }
}
