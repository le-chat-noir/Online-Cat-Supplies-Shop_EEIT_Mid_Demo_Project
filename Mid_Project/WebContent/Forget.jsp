<%@ page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>忘記密碼</title>
	<%@ include file="commonFiles/css/stylesheet.css" %>
</head>
<body>
	<%@ include file="commonFiles/Function.jsp"%>
	<%@ include file="commonFiles/Header.jsp"%>
	<%@ include file="commonFiles/Menu.jsp"%>
	<div id="main_window" class="main_window">
		<h3>忘記密碼</h3>
		<p>請在下方輸入帳號與註冊時所用email</p>
		<form action="SendResetMail" method="post">
			您的帳號<input type="text" id="account" name="account" value="${param.account}" required="required" ><br>
			註冊email<input type="text" id="email" name="email" required="required"><br>
			<input type="button" value="重設密碼" onclick="resetPwd();">
		</form>
		<h3 id="errorMsg" style="color: red;"></h3>
	</div>
	
	<script type="text/javascript">
	window.addEventListener( "pageshow", function ( event ) {
		var historyTraversal = event.persisted || ( typeof window.performance != "undefined" && window.performance.navigation.type === 2 );
		if ( historyTraversal ) {
	    	window.location.reload();
		}
	});
	
	function resetPwd(){
		console.log("hi");
		$.ajax({
			url: "SendResetMail",
			type: "post",
			data: { 
				account: $('#account').val(), 
				email: $('#email').val()
			},
			success: function (data) {
				var reset = eval(data);
				if(reset){
					console.log("hi");
					$("#main_window").load("account/getResetPwd.jsp");
				}else{
					console.log("no");
					$('#errorMsg').html("帳號或 Email 有錯");
				}
     	    }	
  	  });
	}

</script>
</body>
</html>