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
<title>用户列表</title>
<style type="text/css">
table {
	width: 700px;
	border: #ccc solid 1px;
	text-align: center;
	border-collapse: collapse;
}

th {
	background: #999
}

tr, td {
	border: #ccc solid 1px;
}
</style>
<script>
	function deleteUser(id) {
		if (window.confirm("是否删除此数据")) {
			window.location.href = "UserServlet?task=delete&id=" + id;
		}
	}
	function updateUser(id) {
			window.location.href = "UserServlet?task=beforeUpdate&id=" + id;
	}
</script>
</head>
<body style="background-image:url(statics/img/userInfo/userList.png); background-size:cover;">
	<a href="UserInfoServlet?task=select" style="color:white">返回上一级</a>
	<a href="./welcome.jsp" style="color:white">返回主页</a><br>
	<div align="center">
		<div>
			<form method="post" action="UserServlet?task=select" style="color:white">
				昵称：<input type="text" name="username" /> <input type="submit"
					value="查询" />
			</form>
		</div>

		<div>
			<table style="background-color:white; color: black; background-color: #ffffffaa; width: 700px">
				<tr>
					<th>账号</th>
					<th>昵称</th>
					<th>密码</th>
					<th>邮箱</th>
					<th>手机号</th>
					<th>用户状态</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${userList }" var="user">
				<c:if test="${user.isGM !=1 }">
					<tr>
						<td><a href="javascript:updateUser('${user.id }')">${user.id }</a></td>
						<td>${user.name }</td>
						<td>${user.password }</td>
						<td>${user.email }</td>
						<td>${user.phonenum }</td>
						<c:if test="${user.isFreeze == 1}">
							<td style="background-color:red" >
								<font color="white">冻结</font>
							</td>
						</c:if>
						<c:if test="${user.isFreeze == 0}">
							<td>正常</td>
						</c:if>
						<td>
							<a href="javascript:deleteUser('${user.id }')">删除</a>
							<a href="UserServlet?task=freeze&id=${user.id }">冻结</a>
							<a href="UserServlet?task=unfreeze&id=${user.id }">解冻</a> 
						</td>
					</tr>
				</c:if>
				</c:forEach>
				<tr>
					<td colspan="7">
						${pageTools }
					</td>
				</tr>
			</table>
		</div>
	</div>
	<audio src="statics/music/TimeTemple.mp3" hidden="true" autoplay="autoplay" loop="loop"></audio>
</body>
</html>