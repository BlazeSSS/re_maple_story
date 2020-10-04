<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="statics/css/monster.css" />
<title>怪物详细页面</title>
<script type="text/javascript" src="statics/js/jquery.min1.9.1.js"></script>
<script type="text/javascript">
	function left(){
		var name = document.getElementById("monsterName");
		var id = $("#monsterId").text();
		id = id - 1;
		if (id > 1){
			document.getElementById("right").setAttribute("style"," ");
		}else {
			id = 1;
			document.getElementById("left").setAttribute("style","visibility:hidden");
		}
		$.ajax({
			type:"post",
			url:"monster/switch/" + id,
			success:function(result){
				
				$("#monsterId").text(result.id);
				$("#monsterName").text(result.name);
				$("#monsterImg").attr("src","statics/img/monster/" + result.imgPath);
			}
		});
	}
	
	function right(){
		var name = document.getElementById("monsterName");
		var id = $("#monsterId").text();
		id = +id + 1;
		if (id < 7){
			
			document.getElementById("left").setAttribute("style"," ");
		}else{
			
			id = 7;
			document.getElementById("right").setAttribute("style","visibility:hidden");
		}
		$.ajax({
			type:"post",
			url:"monster/switch/" + id,
			success:function(result){
				$("#monsterId").text(result.id);
				$("#monsterName").text(result.name);
				$("#monsterImg").attr("src","statics/img/monster/" + result.imgPath);
			}
		});
	}

</script>
</head>
<body style="background-image:url(statics/img/monster/monsterBg.png); background-size:cover">
	<a href="./welcome.jsp" style="color:white">返回上一级</a>
	<a href="./welcome.jsp" style="color:white">返回主页</a><br>
	<div align="center">
		<div id="left" class="arrow" onclick="left()" style="visibility:hidden"></div>
		<div id="middle">
			<div style="height:200px;margin:200px auto;">
				<p id="monsterId" hidden="">1</p>
				<h2 id="monsterName" style="color:white">黄蘑菇</h2>
				<p style="height:50px;"></p>
				<img id="monsterImg" alt="monster" style="user-select: none;" src="statics/img/monster/mushroom.gif">
			</div>
		</div>
		<div id="right" class="arrow" onclick="right()"></div>
	<marquee><img alt="footer" src="statics/img/monster/footer_bg.png"></marquee>
	</div>
	<audio src="statics/music/WhenTheMorningComes.mp3" hidden="true" autoplay="autoplay" loop="loop"></audio>
</body>
</html>