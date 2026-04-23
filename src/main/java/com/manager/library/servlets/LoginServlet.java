package com.manager.library.servlets;

import com.manager.library.entities.Student;
import com.manager.library.service.LibraryService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LibraryService service = new LibraryService();
        Integer id = Integer.parseInt(req.getParameter("stdId"));
        Student student = service.getStudentById(id);
        if(student!=null){
            HttpSession session = req.getSession();
            session.setAttribute("studentID",student.getId());
            session.setAttribute("studentName",student.getName());
            session.setAttribute("studentEmail",student.getEmail());
            resp.sendRedirect("welcome.jsp");
        }
        else {
            resp.sendRedirect("index.jsp?error=Invalid+ID");
        }
    }
}
