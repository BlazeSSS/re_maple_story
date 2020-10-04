<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>登录界面</title>
<link rel="stylesheet" href="statics/css/home.css" />
<script src="statics/js/jquery.min1.9.1.js" type="text/javascript"></script>
<script>
    $(function() {
        var id = "<%=session.getAttribute("id")%>"
        
        if(id != "null" && id !=null){
            window.location.href = "./welcome.jsp";
        }
        
        $("#newUser").click(function() {
            window.location.href = "register.jsp";
        });
        
        <c:if test="${message != null}">
          alert("${message}");
        </c:if>
    });
</script>
</head>

<body id="loginBody">
    <div id="login">
        <div id="div1"></div>
        <div id="div2"></div>
        <div id="div3">
            <form name="Form01" action="account/login" method="post" >
                <table style="padding:8px">
                    <tr>
                        <td height="30px">
                            <input type="text" id="userId" name="id" style="width: 161px" />
                        </td>
                        <td rowspan="2">
                            <img id="link" src="statics/img/link.png" onclick="submit()" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="password" id="password" name="password" style="width: 161px" />
                        </td>
                    </tr>
                    <tr style="display:none">
                        <td>
                            <input type="text" id="loginAction" name="loginAction" value="1" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <div style="height: 70px;"></div>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <a href="register.jsp"><img id="newUser" src="statics/img/newUser.png" /></a>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
    <audio src="statics/music/login.mp3" hidden="true" autoplay="autoplay" loop="loop"></audio>
</body>
</html>