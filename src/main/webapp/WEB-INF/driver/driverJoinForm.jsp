<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>로그인 / 회원가입 폼 템플릿</title>
        <link rel="stylesheet" href="style.css">
       <style>
       * {
    margin: 0;
    padding: 0;
    font-family: sans-serif;
}
.wrap {
    height: 100%;
    width: 100%;
    background-image: url(img/background.jpg);
    background-position: center;
    background-size: cover;
    position: absolute;
}
.form-wrap {
    width: 380px;
    height: 800px;
    position: relative;
    margin: 6% auto;
    background: #EFFBF2;
    padding: 5px;
    overflow: hidden;
}
.button-wrap {
    width: 230px;
    margin: 35px auto;
    position: relative;
    box-shadow: 0 0 600px 9px #fcae8f;
    border-radius: 30px;
}
.togglebtn {
    padding: 10px 30px;
    cursor: pointer;
    background: transparent;
    border: 0;
    outline: none;
    position: relative;
}
#btn {
    top: 0;
    left: 0;
    position: absolute;
    width: 110px;
    height: 100%;
    background: #00B98E;
    border-radius: 30px;
    transition: .5s;
}
.social-icons {
    margin: 30px auto;
    text-align: center;
}
.social-icons img {
    width: 30px ;
    cursor: pointer;
}
.input-group {
    top: 180px;
    position: absolute;
    width: 280px;
    transition: .5s;
}
.input-field {
    width: 100%;
    padding: 10px 0;
    margin: 5px 0;
    border: none;
    border-bottom: 1px solid #999;
    outline: none;
    background: transparent;
}
.submit {
    width: 85%;
    padding: 10px 30px;
    cursor: pointer;
    display: block;
    margin: auto;
    background: #00B98E;
    border: 0;
    outline: none;
    border-radius: 30px;
}
.checkbox {
    margin: 30px 10px 30px 0;
}
span {
    color: #777;
    font-size: 12px;
    bottom: 68px;
    position: absolute;
}
#login {
    left: 50px;
}
#register {
    left: 450px;
} 
       </style>
</head>
    <body>
        <div class="wrap">
            <div class="form-wrap">
                <div class="button-wrap">
                    <div id="btn"></div>
                    <button type="button" class="togglebtn" onclick="login()">LOG IN</button>
                    <button type="button" class="togglebtn" onclick="register()">REGISTER</button>
                </div>
                <form id="login" action="" class="input-group">
                    <input type="text" class="input-field" placeholder="User name or Email" required>
                    <input type="password" class="input-field" placeholder="Enter Password" required>
                    <button class="submit">Login</button>
                </form>
                <form id="register" action="" class="input-group">
                    <input type="text" class="input-field" placeholder="Enter Id" required>
                    <input type="password" class="input-field" placeholder="Enter Password" required>
                    <input type="name" class="input-field" placeholder="Enter gender" required>
                    <input type="job" class="input-field" placeholder="Enter job" required>
                    <input type="age" class="input-field" placeholder="Enter age" required>
                    <label><input type="checkbox" class="checkbox" value="M">Man</label>
      				<label><input type="checkbox" class="checkbox" value="W">Woman</label>
                    <input type="gender" class="input-field" placeholder="Enter gender" required>
                    <input type="phone" class="input-field" placeholder="Enter phonenumber" required>
                    <input type="carNumber" class="input-field" placeholder="Enter car number" required>
                    <input type="license" class="input-field" placeholder="Enter license" required>
                    
                    <button class="submit">REGISTER</button>
                </form>
            </div>
        </div>
        <script>
            var x = document.getElementById("login");
            var y = document.getElementById("register");
            var z = document.getElementById("btn");
            function login(){
                x.style.left = "50px";
                y.style.left = "450px";
                z.style.left = "0";
            }
            function register(){
                x.style.left = "-400px";
                y.style.left = "50px";
                z.style.left = "110px";
            }
        </script>
    </body>
</html> 