<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/searchList.css">
<title>角色一览</title>
<script type="text/javascript">

	function toDetailHero(id) {
		window.location.href = "HeroServlet?task=detail&id=" + id;
	}
	
</script>
</head>
<body style="background-image: url(statics/img/heroList.png);background-size: cover;">
	<a href="./welcome.jsp" style="color:white">返回上一级</a>
	<a href="./welcome.jsp" style="color:white">返回主页</a><br>
	<div align="center">
		<div>
			<form id="searchForm" method="post" action="HeroServlet?task=select" style="color:white">
				角色名：<input type="text" name="heroname" />
				<input type="submit" value="查询" />
			</form>
		</div>

		<div >
			<table style="background-color:white; color: black; background-color: #ffffffaa; width: 700px">
				<tr>
					<th>编号</th>
					<th>角色名</th>
					<th>职业群</th>
					<th>职业类型</th>
				</tr>
				<c:if test="${heroList != null }">
				<c:forEach items="${heroList }" var="hero">
					<tr>
						<td>${hero.id }</td>
						<td><a href="HeroServlet?task=detail&id=${hero.id }">${hero.name }</a></td>
						<td>${hero.race }</td>
						<td>${hero.type }</td>
					</tr>
				</c:forEach>
				</c:if>
				<tr>
					<td colspan="4">
						${pageTools }
					</td>
				</tr>
			</table>
		</div>
	</div>
	<audio src="statics/music/FlowerInBlue.mp3" hidden="true" autoplay="autoplay" loop="loop"></audio>
</body>
</html>