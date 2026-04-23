<!doctype html>
<html lang="en" xmlns:style="http://www.w3.org/1999/xhtml" xmlns:padding="http://www.w3.org/1999/xhtml"
      xmlns:margin-left="http://www.w3.org/1999/xhtml" xmlns:margin-top="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Document</title>
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
        width:250px;
        border-radius:30px;
        border:3px rgba(255, 255, 255, 0.2) solid;);
        background-color: rgba(255, 255, 255, 0.1);
        height:300px;
        box-shadow: 0px 0px 10px 1px rgba(255, 255, 255, 0.3);
        }
        label {
  display: block; /* Forces the label to sit on its own line */
  color: white;
  margin-bottom: 8px; /* Adds space between the label and the input */
  font-family: sans-serif;
  font-weight: bold;
}
        input:focus {
  outline: none; /* Removes the ugly default browser outline */
  border-color: #00ffff; /* Changes border to cyan */
  box-shadow: 0 0 10px rgba(0, 255, 255, 0.3); /* Adds a subtle glow */
}
        .btn{
            background-color:white;
            border-radius: 10px;
            padding: 8px 60px;
            border:2px solid gray;
        }
        .field{
            background-color:white;
            border-radius: 10px;
            border:2px solid gray;
            padding: 7px 10px;
        }
        .btn:active {
  transform: scale(0.95); /* Slight shrink effect when clicked */
}

    </style>
</head>
<body>


<div class="bg-card">
    <label style="margin-top: 15px; font-size:20px" >Login</label>
    <form action="loginServlet" method="post">
    <div class="box">
        <label style="margin-right :90px; margin-top:50px;">Student ID</label>
        <input class="field" type="number" name="stdId" placeholder="Enter your student ID">
    </div>
    <button type="submit" class="btn" style = "margin-top:20px;" >Login</button>
      </form>
    <label style="margin-top: 15px;">New Student?</label>
    <a  href="register.jsp" class="btn" type="button" id="register" name="reg">Register</a>

</div>

</body>
</html>