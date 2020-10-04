<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="statics/css/welcome.css" />
<script src="statics/js/jquery.min1.9.1.js" type="text/javascript"></script>
<title>欢迎页面</title>
<script>
	$(function(){
		var userid = "<%=session.getAttribute("id")%>"
		
		if(userid == "null" || userid == null){
			window.location.href = "./login.jsp";
		}
	});
	
	function toHero(){
		window.location.href = "HeroServlet?task=init"
	}
	function toMonster(){
		window.location.href = "MonsterServlet"
	}
	function toCollection(){
		window.location.href = "MyCollection"
	}
	
	function logout(){
		window.location.href = "account/logout";
	}
	function toUserCenter(){
		window.location.href = "UserInfoServlet?task=select";
	}
</script>
</head>
<body style='margin: 0; background-image: url(statics/img/bg.png); background-size: cover; text-align: center'>
	<div style='background-image: url(statics/img/master.png); width: 1533px; height: 629px; position: relative; display: inline-block'>
	
		<div class="loginInfo" align='right'>
			<span><%=session.getAttribute("id")%></span><br>
			<span><%=session.getAttribute("name")%></span><br>
			<span>登录成功</span><br>
			<span id="userCenter" onclick="toUserCenter()">个人中心</span>
			<span id="logout" onclick="logout()">退出</span>
		</div>
		<div align="center">
			<table>
				<tr>
					<td>
						<div class="toDetail" style="background-image: url(statics/img/hero.png);" onclick="toHero()"></div>
					</td>
					<td>
						<div class="toDetail" style="background-image: url(statics/img/monster.png);" onclick="toMonster()"></div><br>
					</td>
					<td>
						<div class="toDetail" style="background-image: url(statics/img/collection.png);" onclick="toCollection()"></div><br>
					</td>
				</tr>
				<tr>
					<td><h2 align="center" style = "color:white">角色</h2></td>
					<td><h2 align="center" style = "color:white">怪物</h2></td>
					<td><h2 align="center">我的收藏</h2></td>
				</tr>
			</table>
		</div>
		<div style="position:absolute;bottom: 0;width:1533px" align="center">
			<img src='statics/img/pig.gif' style="position: absolute; bottom: 0; left: 30%"/> 
			<img src='statics/img/pinkbin.gif' style="position: absolute; bottom: 0; left: 42%"/> 
			<img src='statics/img/mushroom.gif' style="position: absolute; bottom: 0; right: 30%"/>
		</div>
	</div>
	<div style="position: absolute; right: 0; top: 0">
		<img src='statics/img/userInfo.png'/>
		<div style="position: absolute; top: 0; right: 0; margin-top: 17px; margin-right: 15px; text-align: right">
	       <span><%=session.getAttribute("id")%></span><br>
	       <span><%=session.getAttribute("name")%></span><br>
	       <span>登录成功</span><br>
	       <span id="userCenter" onclick="toUserCenter()">个人中心</span>
	       <span id="logout" onclick="logout()">退出</span>
		</div>
	</div>
	<div style='height: calc(100vh - 629px); width: 100%; background-image: url(statics/img/ground.png); position: absolute; bottom: 0px;'></div>
	<audio src="statics/music/RestNPeace.mp3" hidden="true" autoplay="autoplay" loop="loop"></audio>
</body>
</html>