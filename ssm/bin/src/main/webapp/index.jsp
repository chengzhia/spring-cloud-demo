<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" language="javascript" src="${pageContext.servletContext.contextPath}/js/jquery-1.8.3.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
	body {
	font-size: 12px;text-align: center
	}
	div,span{float: left;border:solid 1px #ccc;margin:8px;display: none}
	.clsFraA{width:65px;height:65px}
	.clsFraP{width:45px;height:45px;background-color: #eee}
	.clsFraC{width:25px;heihet:25px;background-color: #ddd}
</style>
<script type="text/javascript">
$(function(){
	$("#divMid").css("display","block")
	$("div span").css("display","block")
})
</script>
</head>
<body>
	<div class="clsFraA">left</div>
	<div class="clsFraA" id="divMid">
		<span class="clsFraP" id="span1">
			<span class="clsFraC" id="span2"></span>
		</span>
	</div>
	<div class="clsFraA">right_1</div>
	<div class="clsFraA">regiht_2</div>
</body>
</html>