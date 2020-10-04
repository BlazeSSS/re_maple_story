<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注册页面</title>
<link rel="stylesheet" href="statics/css/home.css" />
<style>
span {
    color: red;
}
</style>
<script>
            var ckl = false;
            var ckpw = false;
            var cke = false;

            function numOnly() {
                var pattern = /\d/;
                //var patternlow = "9[6-9]|10[0-5]";//匹配小键盘 || patternlow.test(keyChar)
                var keyChar = String.fromCharCode(event.keyCode);
                return pattern.test(keyChar)  || (event.keyCode == 8) || (event.keyCode == 46);
            }

            function nameOnly() {
                var pattern = /\w/;
                var keyChar = String.fromCharCode(event.keyCode);
                return pattern.test(keyChar) || (event.keyCode == 8) || (event.keyCode == 46);
            }

            function checkLength(self) {
                var str;
                if(self.id == "user") {
                    str = "userTip"
                } else if(self.id == "password") {
                    str = "pwTip"
                }
                if(self.value.length <= 3) {
//                                        document.getElementById(str).innerHTML = "*长度不能小于3";
                    document.getElementById(str).innerHTML = "X";
                    ckl = false;
                } else {
                    document.getElementById(str).innerHTML = "";
                    ckl = true;
                }
            }

            function checkpww(self) {
                var firstPassword = document.getElementById("password").value;
                var secondPassword = self.value;
                if(firstPassword != secondPassword) {
//                    document.getElementById("checkpwTip").innerHTML = "*密码前后不相同";
                    document.getElementById("checkpwTip").innerHTML = "X";
                    ckpw = false;
                } else {
                    document.getElementById("checkpwTip").innerHTML = "";
                    ckpw = true;
                }
            }

            function checkEmail(self) {
                var pattern = /^\w+@\w+\.\w+\.?\w+$/;
                if(pattern.test(self.value)) {
                    document.getElementById("emailTip").innerHTML = "";
                    cke = true;
                } else {
//                    document.getElementById("emailTip").innerHTML = "*邮箱格式错误";
                    document.getElementById("emailTip").innerHTML = "X";
                    cke = false;
                }
            }

            function checkAll() {
                if(!(ckl && ckpw && cke)) {
                    alert("注册失败！请填入正确的信息")
                }else{
                    document.forms['Form02'].submit();
                }
                return ckl && ckpw && cke;
            }
            
            function backToLogin(){
                window.location.href="./login.jsp"
            }
        </script>
</head>
<body id="registerBody">

    <div id="register">
        <div id="rdiv1"></div>
        <div id="rdiv2"></div>
        <div id="formDiv">
            <form action="Register" name="Form02" method="post">
                <table>
                    <tr>
                        <td>账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</td>
                        <td><input type="text" id="user" name="userid"
                            placeholder="输入长度不能小于3" onkeydown="return nameOnly()"
                            onblur="checkLength(this)" /></td>
                        <td><span id="userTip"></span></td>
                    </tr>
                    <tr>
                        <td>昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</td>
                        <td><input type="text" name="username" maxlength="10" /></td>
                    </tr>
                    <tr>
                        <td>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</td>
                        <td><input type="password" id="password" name="password"
                            placeholder="输入长度不能小于3" onblur="checkLength(this)" /></td>
                        <td><span id="pwTip"></span></td>
                    </tr>
                    <tr>
                        <td>确认密码</td>
                        <td><input type="password" id="checkpw" placeholder="再次输入密码"
                            onblur="checkpww(this)" /></td>
                        <td><span id="checkpwTip"></span></td>
                    </tr>
                    <tr>
                        <td>电子邮箱</td>
                        <td>
                            <input type="email" name="email" onblur="checkEmail(this)" />
                        </td>
                        <td><span id="emailTip"></span></td>
                    </tr>
                    <tr>
                        <td>手机号码</td>
                        <td>
                          <input type="text" name="phonenum" maxlength="11" onkeydown="return numOnly()" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3">
                            <div style="height: 95px;"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center">
                            <img id="sure" src="statics/img/sure.png" onclick="checkAll()"/>&nbsp;&nbsp;
                            <img id="cancel" src="statics/img/cancel.png" onclick="backToLogin()"/>
                        </td>
                    </tr>
                </table>

            </form>
        </div>
    </div>
    <audio src="statics/music/login.mp3" hidden="true" autoplay="autoplay" loop="loop"></audio>
</body>
</html>