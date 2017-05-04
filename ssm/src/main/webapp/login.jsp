<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.servletContext.contextPath }/js/jquery-1.8.3.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<form id="form" action="${pageContext.servletContext.contextPath }/user/LoginSSO.html" method="post">
		<input type="text" name="name"/><br/>
		<input type="password" name="password"/><br/>
		<input type="submit" value="登录" id="login"/>
		<input type="submit" value="注册" id="register"/>
	</form>
		<input type="button" id="btn" onclick="btn()" value="jsonp">
</body>
<script type="text/javascript">
function login(){
	path="${pageContext.servletContext.contextPath }/user/login.html"
		$('#form').attr('action',path).submit();
	}
	
/* function register(){
	path="${pageContext.servletContext.contextPath }/user/register.html"
		$('#form').attr('action',path).submit();
	} */
	function btn(){
		 $.ajax({
			type : "GET",
			url : "${pageContext.servletContext.contextPath}/user/jsonp.html",
			dataType : "jsonp",
			timout:500,
			success : function(data){
				alert(data.list)	
			}
		}) 
	}
</script>
</html>