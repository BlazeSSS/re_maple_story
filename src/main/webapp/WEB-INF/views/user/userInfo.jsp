<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="statics/js/jquery.min1.9.1.js" type="text/javascript"></script>
<style type="text/css">
#pwTip, #emailTip {
    color: red;
    font-size: 11px
}
</style>
<title>个人中心</title>
<script>
	var ckl = true;
	var cke = true;

	function numOnly() {
		var pattern = /\d/;
		var keyChar = String.fromCharCode(event.keyCode);
		return pattern.test(keyChar)  || (event.keyCode == 8) || (event.keyCode == 46);
	}

	function checkLength(self) {
		if(self.value.length <= 3) {
			document.getElementById("pwTip").innerHTML = "*长度不能小于3";
			ckl = false;
		} else {
			document.getElementById("pwTip").innerHTML = "";
			ckl = true;
		}
	}

	function checkEmail(self) {
		var pattern = /^\w+@\w+\.\w+\.?\w+$/;
		if(pattern.test(self.value)) {
			document.getElementById("emailTip").innerHTML = "";
			cke = true;
		} else {
			document.getElementById("emailTip").innerHTML = "*邮箱格式错误";
			cke = false;
		}
	}

	function checkAll() {
		if(!(ckl && cke)) {
			alert("修改失败！请填入正确的信息");
		}else{
			alert("修改成功！请重新登录")
			document.forms['Form01'].submit();
		}
		return ckl && cke;
	}
	
	function undis(){
		var ele = document.getElementsByTagName("input");
		for(var i = 0; i < ele.length; i++){
			ele[i].removeAttribute("disabled");
		}
		
	}
</script>
</head>
<body style="background-image:url(statics/img/userInfo/userBg.png)">
	<a href="./welcome.jsp" style="color:black">返回主页</a><br>
	<div style="margin: 100px auto" align="center">
		<c:if test="${user.isGM==1}">
			<h1>你好！管理员</h1>
		</c:if>
		<h3>个人信息</h3>
		<form action="userInfo/update" name="Form01" style="margin:0" method="post">
		<table style="background-color:white; color: black; background-color: #ffffffaa; width: 320px">
			<tr>
				<td colspan="3" align="center">
					<c:if test="${user.isGM==1}">
						<span><a href="userInfo/queryLike">查看所有用户</a></span>
					</c:if>
				</td>
			</tr>
			<tr>
				<td width="100px"  >
					<p>用户账号</p>
				</td>
				<td width="100px">${user.id}</td>
			</tr>
			<tr style="display:none">
                <td>
                    <input id="userId" type="text" name="id" disabled="disabled" value="${user.id}"/>
                </td>
            </tr>
			<tr>
				<td >
					<p>用户昵称</p>
				</td>
				<td>
					<input id="name" type="text" name="name" disabled="disabled" value="${user.name}"/>
				</td>
			</tr>
			<tr>
				<td>
					<p>用户密码</p>
				</td>
				<td>
					<input type="text" name="password" disabled="disabled" value="${user.password}" onblur="checkLength(this)"/>
				</td>
			</tr>
			<tr>
			    <td></td> 
			    <td><span id="pwTip"></span></td>
		    </tr>
			<tr>
				<td>
					<p>用户邮箱</p>
				</td>
				<td>
					<input type="text" name="email" disabled="disabled" value="${user.email}" onblur="checkEmail(this)"/>
				</td>
			</tr>
            <tr>
                <td></td> 
                <td><span id="emailTip"></span></td>
            </tr>
			<tr>
				<td>
					<p>手机号码</p>
				</td>
				<td>
					<input type="text" name="phonenum" disabled="disabled" maxlength="11" value="${user.phonenum}" onkeydown="return numOnly()"/>
				</td>
			</tr>
		</table>
		</form>
		<div style="background-color:white; color: black; background-color: #ffffffaa; width: 320px">
			<button onclick="undis()">修改</button>
			<input type="button" value="提交" disabled="disabled" onclick="checkAll()"><br>
			<span>&nbsp;</span>
		</div>
	</div>
	<audio src="statics/music/Nautilus.mp3" hidden="true" autoplay="autoplay" loop="loop"></audio>
</body>
</html>