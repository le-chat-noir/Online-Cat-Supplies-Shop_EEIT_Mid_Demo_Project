<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="commonFiles/css/stylesheet.css" %>

</head>
<body>

		<%@ include file="commonFiles/Header.jsp"%>

		<div id="main_window" class="main_window">
	<form id="resetForm" action="DoResetPwd" method="post">
	<input style="display: none;" name="account" value="${param.account}">
	<input style="display: none;" name="uid" value="${param.uid}">
	<input style="display: none;" name="email" value="${param.email}">
	<label>密碼:</label>
	<span class="note">至少8個字，必須同時有英文+數字。<span style="color: red">(必填)</span></span>
	<br>
	<input type="password" id="password" name="password" minlength=8 maxlength=15 required="required" onblur="checkPasswordRule();">
	<span id="passMsg"></span>
	<br>

	<label>確認密碼:</label>
	<br>
	<input type="password" id="verifyPwd" name="verifyPwd" minlength=8 maxlength=15 required="required" onblur="verifyPassword();">
	<span id="passVerMsg"></span>
	<br>
	<hr>
	<input type="button" value="變更密碼" onclick="checkAll();">
	</form>
	</div>
	
	<script type="text/javascript">
	function checkPasswordRule() {
		var password = $("[name='password']").val().trim();
		if(password.length < 8){
			$("#passMsg").html("長度要大於8").css("color", "red");
			return false;
		}else if(/\d/.test(password) && /[a-zA-Z]/.test(password) ) { 
			$("#passMsg").html("OK").css("color", "green")
			return true;
		}else {
			$("#passMsg").html('需要同時有 "英文字母" 和 "數字"').css("color", "red");
			return false;
		}
	}
	function verifyPassword() {
		var checkSame = ($("[name = 'password']").val() == $("[name='verifyPwd']").val() );
		if(checkSame && checkPasswordRule()){
			$("#passVerMsg").html("OK").css("color", "green")
			return true;
		}else{
			$("#passVerMsg").html("密碼不一致").css("color", "red");
			return false;
		}
	}
	function checkAll() {
			if ( verifyPassword() && verifyPassword() ){
				$("#resetForm").submit();
			}else {
				
			}
	}
	</script>
</body>
</html>