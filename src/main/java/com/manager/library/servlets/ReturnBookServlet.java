package com.manager.library.servlets;

import com.manager.library.entities.Book;
import com.manager.library.entities.Student;
import com.manager.library.service.LibrayServiceWeb;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/returnBook")
public class ReturnBookServlet extends HttpServlet {
    private LibrayServiceWeb libraryService = new LibrayServiceWeb();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if(session==null || session.getAttribute("studentID") == null){
            resp.sendRedirect("index.jsp");
            return;
        }
        String bBookId = req.getParameter("bookId");
        boolean isReturned = false;

        if(!bBookId.trim().isEmpty() && bBookId!=null){
            try{
                int bookId = Integer.parseInt(bBookId);
                Book book = libraryService.getBookById(bookId);
                Student student = libraryService.getStudentById((Integer)session.getAttribute("studentID"));
                if(book!=null){
                    if(libraryService.returnBook(book,student))
                        isReturned = true;
                    req.setAttribute("isReturned",isReturned);
                    req.setAttribute("returnedBook",libraryService.getBookById(bookId));
                }else {
                    req.setAttribute("errMsg", "No book found for this id!");
                }
            }catch (NumberFormatException n)
            {
                req.setAttribute("errMsg", "Invalid ID format, Enter Number Only");
            }
        }
        req.getRequestDispatcher("returnBook.jsp").forward(req,resp);

    }
}
