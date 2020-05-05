<%@ page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>樂透製造機</title>
		<%@ include file="commonFiles/css/stylesheet.css" %>
	</head>
	<body>
		<%@ include file="commonFiles/Function.jsp"%>
		<%@ include file="commonFiles/Header.jsp"%>
		<%@ include file="commonFiles/Menu.jsp"%>
		<div id="main_window" class="main_window">
			<div id="jsp_content">
				<h1>已經登出</h1>
			</div>
		</div>
		
		<script type="text/javascript">
		window.addEventListener( "pageshow", function ( event ) {
			var historyTraversal = event.persisted || ( typeof window.performance != "undefined" && window.performance.navigation.type === 2 );
			if ( historyTraversal ) {
			    window.location.reload();
			}
		});
		
		
		
	
		</script>
	</body>
</html>