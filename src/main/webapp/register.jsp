<!doctype html>
<html lang="en" xmlns:style="http://www.w3.org/1999/xhtml" xmlns:padding="http://www.w3.org/1999/xhtml"
      xmlns:margin-left="http://www.w3.org/1999/xhtml" xmlns:margin-top="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Document</title>
    <style>
         body   {
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
                    height:400px;
                    box-shadow: 0px 0px 10px 1px rgba(255, 255, 255, 0.3);
                     }
                label {
                    display: block;
                    color: white;
                    margin-bottom: 5px;
                    font-family: sans-serif;
                    font-weight: bold;
                       }
           input:focus {
                      outline: none;
                      border-color: #00ffff;
                      box-shadow: 0 0 10px rgba(0, 255, 255, 0.3);
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
        <form method="post" action="registerServlet">
    <label style="margin-top: 15px; font-size:20px" >Register</label>
    <div class="box">
                     <label style="margin-right :90px; margin-top:10px;"> Full Name</label>
                     <input class="field" type="text" name="userName" placeholder="Enter your name" required>
    </div>
    <div class="box">
                     <label style="margin-right :90px; margin-top:10px;"> Email</label>
                     <input class="field" type="email" name = "userEmail"  placeholder="Enter your email" required>
    </div>
    <div class="box">
                    <label style="margin-right :90px; margin-top:10px;"> Phone Number</label>
                    <input class="field" type=number  length=10  name="userPhone" placeholder="Enter your phone number" required>
    </div>

    <button  class="btn" type="submit" style = "margin-top:20px;" id="register" name="register">Register</button>
    </form>
    <label style="margin-top: 15px;">already account?</label>
    <a href="index.jsp" type="button" class="btn"  name="sign-in">sign in</a>

</div>

</body>
</html>