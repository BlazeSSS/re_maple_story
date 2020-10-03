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
<style type="text/css">
#collect:hover{
	cursor:pointer;
}
#panel{
	top:0;
	right:100px;
	display:none;
	position:absolute;
}
</style>
<script src="statics/js/jquery.min1.9.1.js" type="text/javascript"></script>
<title>角色详细页面</title>
<script type="text/javascript">
	$(function(){
		
		var heroId = ${hero.id};
		var collection = "${user.heroCollection}".split(",").map(function(num){return +num});
		if(collection.indexOf(heroId) < 0){
			
			$("#collect").attr("src","statics/img/collect.png");
			$("#collect").attr("title","收藏");
		}else{
			$("#collect").attr("src","statics/img/uncollect.png");
			$("#collect").attr("title","取消收藏 ");
		}
		
		$("#collect").click(function(){
			if($("#collect").attr("title") == "收藏"){
				$("#panel").html("<img alt='取消收藏' src='statics/img/hero/uncollect.png'>");
			} else {
				$("#panel").html("<img alt='收藏成功' src='statics/img/hero/collect.png'>");
			}
			$("#panel").slideToggle("normal");
			setTimeout(foo,700);
		});
	});
	
	function foo(){
	    $("#panel").slideToggle("normal");
	}
	
	function back(){
		
		window.history.back();
	}
	function collection(){
		
		var heroId = ${hero.id};
		var collection = $("#collection").text().split(",").map(function(num){return +num});
		var task;
		
		if(collection.indexOf(heroId) < 0){
			task = "collect";
			$("#collect").attr("src","statics/img/uncollect.png");
			$("#collect").attr("title","取消收藏");
		}else{
			task = "uncollect";
			$("#collect").attr("src","statics/img/collect.png");
			$("#collect").attr("title","收藏");
		};
		
		$.ajax({
			type:"get",
			url:"UserInfoServlet?task=" + task +"&heroId="+heroId,
			data:{
			},
			success:function(result){
				$("#collection").text(result);
			}
		});
	}
</script>
</head>
<body style="background-image: url(statics/img/hero/heroBg.png);background-size: cover;">
	<a href="javascript:back()" style="color:white">返回上一级</a>
	<a href="./welcome.jsp" style="color:white">返回主页</a>
	<div id="panel"></div>
	
	<div style="width:auto" align="center">
		<table>
			<tr>
				<td height="60px" width="450px"  align="center">
					<div>
						<h1 style="display:inline-block;">
							<span style = "color:white">
								 ${hero.name }
							</span>
						</h1>
						<img id="collect" alt="是否收藏.png" title="" src="" onclick="collection()">
					</div>
				</td>
				<td>
					<div>
						<span style = "color:white">
							职业群：${hero.race }
						</span><br>
						<span style = "color:white">
							职业类型：${hero.type }
						</span><br>
					</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<img title="${hero.name }" src="statics/img/hero/${hero.charPicPath }" alt="heroName">
					</div>
				</td>
				<td>
					<div>
						<img src="statics/img/hero/${hero.introPicPath }" alt="heroIntro">
					</div>
				</td>
			</tr>
		</table>
	</div>
	<span id="collection" hidden="true">${user.heroCollection }</span>
	<audio src="statics/music/QueensGarden.mp3" hidden="true" autoplay="autoplay" loop="loop"></audio>
</body>
</html>