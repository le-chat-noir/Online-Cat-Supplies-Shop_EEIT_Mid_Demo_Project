<%@ page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Quick Check Out</title>
	<%@ include file="commonFiles/css/stylesheet.css" %>
</head>
<body onload="checkError()">
	<%@ include file="commonFiles/Function.jsp"%>
	<%@ include file="commonFiles/Header.jsp"%>
	<%@ include file="commonFiles/Menu.jsp"%>
	<div id="main_window" class="main_window">
	<h3>Quick Check Out: 使用者登入</h3>
	<form action="quickLogin" method="post">
		<input type="text" id="account" name="account" minlength="3" required="required"><br> 
		<input type="password" id="password" name="password" minlength="8" required="required"><br> 
		<input type="text" name="cart" value="global" style="display: none;" readonly="readonly">
		<hr>
		<input type="button" value="DEMO" onclick="autofill();">
		<input type="button" value="DEMO2" onclick="autofill2();">  
		<input type="submit" value="送出"> 
		<input type="reset" value="清除">
		<input type="button" value="忘記密碼" onclick="goForget();"> 
	</form>
	<div id="error" style="color: red; font-weight: bold;"></div>
	<script type="text/javascript">
		window.addEventListener( "pageshow", function ( event ) {
			var historyTraversal = event.persisted || ( typeof window.performance != "undefined" && window.performance.navigation.type === 2 );
			if ( historyTraversal ) {
		    	window.location.reload();
			}
		});
	
		function autofill() {
			$("#account").val("kat");
			$("#password").val("pika0101");
		}
		
		function autofill2() {
			$("#account").val("Cat");
			$("#password").val("pika0101");
		}
		function checkError() {
			if ( '${param.LoginError}' != 0){
				$("#error").html("Oops! Wrong account or password");
			} 
		}
		function goForget(){
			window.location.href="Forget.jsp?account="+$("#account").val();
		}
	</script>
</body>
</html>