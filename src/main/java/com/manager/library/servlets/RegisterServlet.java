package com.manager.library.servlets;

import com.manager.library.entities.Student;
import com.manager.library.service.LibrayServiceWeb;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String name = req.getParameter("userName");
        String email = req.getParameter("userEmail");
        String phone = req.getParameter("userPhone");
        Student student = new Student();
        student.setPhone(phone);
        student.setEmail(email);
        student.setName(name);
        LibrayServiceWeb service = new LibrayServiceWeb();
        PrintWriter out = resp.getWriter();
        if(!service.emailExists(student.getEmail())){
            service.addNewStudent(student);
            out.println("<h1>Student Registered!<h1>");
            out.println("<h1>Student detail :<h1>");
            out.printf("<h1>%s<h1>",student.toString());
            out.println("<h1>Remember your id for future login <h1>");
            out.println("<a href=\"index.jsp\">GO TO LOGIN PAGE</a>");
        }
        else{
            out.println("<h1>Email Already Exists!<h1>");
            out.println("<h2>Try again with Different Email<h2>");
            out.println("<a href=\"register.jsp\">Go To Register Page</a>");
        }


    }
}
