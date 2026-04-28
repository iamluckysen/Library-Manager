<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.manager.library.entities.Book" %>

<%
    if (session == null || session.getAttribute("studentID") == null) {
        response.sendRedirect("index.jsp");
        return;
    }

    Book foundBook = (Book)request.getAttribute("foundBook");
    String errMsg = (String)request.getAttribute("errMsg");
    %>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Search Book</title>
        <style>
            body {
                color: white;
                font-family: Helvetica, Sans-Serif;
                display: flex;
                background: linear-gradient(45deg, #185377, #0F1C33);
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
            }
            .bg-card {
                text-align: center;
                width: 400px;
                border-radius: 30px;
                border: 3px rgba(255, 255, 255, 0.2) solid;
                background-color: rgba(255, 255, 255, 0.1);
                /* Changed height to auto so it grows when book details show up */
                height: auto;
                min-height: 300px;
                padding-bottom: 30px;
                box-shadow: 0px 0px 10px 1px rgba(255, 255, 255, 0.3);
                padding-top: 20px;
            }
            .field {
                background-color: white;
                border-radius: 10px;
                border: 2px solid gray;
                padding: 10px;
                width: 60%;
                margin-top: 15px;
            }
            .btn {
                background-color: white;
                color: black;
                border-radius: 10px;
                padding: 8px 30px;
                border: 2px solid gray;
                cursor: pointer;
                margin-top: 15px;
                font-weight: bold;
            }
            .btn:active {
                transform: scale(0.95);
            }
    .result-box {
                margin-top: 30px;
                padding: 15px;
                background-color: rgba(0, 0, 0, 0.2);
                border-radius: 15px;
                text-align: left;
                width: 80%;
                margin-left: auto;
                margin-right: auto;
            }
            .error {
                color: #ff4d4d;
                margin-top: 20px;
                font-weight: bold;
            }
        </style>
    </head>
    <body>

    <div class="bg-card">
        <h2 style="margin-top: 10px;">Search Library</h2>

        <form action="searchBook" method="get">
            <label style="display: block; margin-top: 20px;">Enter Book ID:</label>
            <input class="f
            ield" type="number" name="bookId" required placeholder="e.g., 2">
            <br>
            <button type="submit" class="btn">Search</button>
        </form>

        <% if (errMsg != null) { %>
            <div class="error"><%= errMsg %></div>
        <% } %>

        <% if (foundBook != null) { %>
            <div class="result-box">
                <h3 style="margin-top: 0; text-align: center; color: #00ffff;">Book Found!</h3>
                <p><strong>ID:</strong> <%= foundBook.getId() %></p>
                <p><strong>Title:</strong> <%= foundBook.getName() %></p>
                <p><strong>Author:</strong> <%= foundBook.getAuthor() %></p>
                <p><strong>Available:</strong> <%= foundBook.getTotalQuantity() %></p>
            </div>
        <% } %>

        <div style="margin-top: 30px;">
            <button class="btn" onclick="window.location.href='welcome.jsp'">Back to Home</button>
        </div>

    </div>

    </body>
    </html>