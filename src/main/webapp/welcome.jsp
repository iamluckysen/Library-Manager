<%@ page import="javax.servlet.http.*" %>
<%                      Integer id = null;
                        String name = null;
                        String email = null;
       if(session!=null && session.getAttribute("studentID")!=null){
                     id =  (Integer)session.getAttribute("studentID");
                     name =   (String)session.getAttribute("studentName");
                     email = (String)session.getAttribute("studentEmail");
        }
        else{
        response.sendRedirect("index.jsp");
        return;}


%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>welcome</title>
    <style>
        body{
        color:white;
         font-family: Helvetica, Sans-Serif;
        display : flex;
        background: linear-gradient(45deg, #185377,#0F1C33);
        justify-content: center;
        align-items: center;
        height: 100vh;
        margin:0;
        }
        .bg-card{
        text-align: center;
        width:600px;
        border-radius:30px;
        border:3px rgba(255, 255, 255, 0.2) solid;);
        background-color: rgba(255, 255, 255, 0.1);
        height:500px;
        box-shadow: 0px 0px 10px 1px rgba(255, 255, 255, 0.3);
        }
        .btn{
            background-color:white;
            border-radius: 10px;
            padding: 15px 50px;
            margin-top : 10px;
            border:2px solid gray;
        }
        .btn:active {
  transform: scale(0.95);
}   .butn:active {
  transform: scale(0.95);
}

    </style>
</head>
<body>

<div class="bg-card">
    <label style="margin-top: 15px; font-size:30px" >Welcome <%= name %> !</label>
    <div> <button  class="btn" style="margin-top:80px"  name="A">See All Book List</button></div>
    <div> <button  class="btn"   name="M"> See My Book List </button></div>
    <div><button  class="btn"   name="S">Search Book By ID</button></div>
    <div><button  class="btn"   name="B">Borrow a new Book</button></div>
    <div><button  class="btn"   name="R">Return Book</button></div>
   <div> <button  class="btn"    name="O">Logout</button></div>
</div>

</body>
</html>