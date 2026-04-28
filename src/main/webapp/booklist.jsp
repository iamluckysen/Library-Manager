
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.manager.library.entities.Book" %>
<%
    String studentName = (String) session.getAttribute("studentName");
    if (studentName == null) {
        response.sendRedirect("index.jsp");
        return;
    }
    List<Book> bookList = (List<Book>) request.getAttribute("bookList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Library Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            padding: 40px;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        table {
            width: 80%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
            box-shadow: 0 4px 8px rgba(0,0,0,0.1);
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th { background-color: #0056b3; color: white; }
        tr:nth-child(even) { background-color: #f9f9f9; }
        tr:hover { background-color: #f1f1f1; }
    </style>
</head>
<body>

    <h2>Welcome <%= studentName %>!</h2>
    <h3>Library Catalog</h3>

    <table>
        <thead>
            <tr>
                <th>Book ID</th>
                <th>Title</th>
                <th>Author</th>
                <th>Available Quantity</th>
            </tr>
        </thead>
        <tbody>
            <% if (bookList != null) {
                    for (Book book : bookList) {
            %>
                        <tr>
                            <td><%= book.getId() %></td>
                            <td><%= book.getName() %></td>
                            <td><%= book.getAuthor() %></td>
                            <td><%= book.getTotalQuantity() %></td>

                        </tr>
            <%
                    }
                } else {
            %>
                    <tr>
                        <td colspan="4" style="text-align:center;">No books found in the library.</td>
                    </tr>
            <%  }%>
        </tbody>
    </table>
</body>
</html>