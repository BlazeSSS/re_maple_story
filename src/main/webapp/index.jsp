<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC " //W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>hello</title>
<body>
<h2>Hello World!</h2>
<h2>中文测试!</h2>
<c:forEach begin="1" end="100" var="sn">
	${sn }
</c:forEach>
</body>
</html>
