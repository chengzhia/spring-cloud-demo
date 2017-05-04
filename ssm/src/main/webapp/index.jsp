<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="javascript" src="${pageContext.servletContext.contextPath}/js/jquery-1.8.3.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function returnController(){
		 $.ajax({
				type : "GET",
				async:false,
				url : "${pageContext.servletContext.contextPath}/test/return.html",
				timout:500
			}) 
	}
</script>
</head>
<body>
	<input type="button" value="提交" onclick="returnController();"/>
</body>
</html>