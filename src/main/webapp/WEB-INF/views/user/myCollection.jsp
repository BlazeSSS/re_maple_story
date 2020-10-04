<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的收藏</title>
<script type="text/javascript">
	function toDetailHero(id){
		
		window.location.href = "hero/detail/" + id;
	}
	function back(){
		
		window.history.back();
	}
</script>
<style type="text/css">
img:hover{
	cursor:pointer;
}
</style>
</head>
<body style="background-image:url(statics/img/collection/collectionBg.png); background-size:cover;">
	<a href="javascript:back()" style="color:white">返回上一级</a>
	<a href="./welcome.jsp" style="color:white">返回主页</a>
	<br><br><br><br><br>
	<div style="width:500px;margin:0 auto;" align="center">
		<c:forEach items="${heroList }" var="hero">
			<img title="${hero.name }" alt="${hero.name }" 
			     src="statics/img/collection/${hero.charPicPath }"
			     onclick="toDetailHero(${hero.id})"
			     style="padding:10px">
		</c:forEach>
	</div>
	<audio src="statics/music/FlowerInBlue.mp3" hidden="true" autoplay="autoplay" loop="loop"></audio>
</body>
</html>