<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="padding-top: 50px;">
<table style="background-color: rgba(128, 0, 128, 0.2); border-radius: 10px;width: 600px; height: 200px;">
<tr><td style="vertical-align: middle;">
	<h3>使用者登入</h3>
	<form action="LoginProcess" method="post">
		<input type="text" id="account" name="account" minlength="3" required="required"><br> 
		<input type="password" id="password" name="password" minlength="8" required="required"><br> 
		<input type="button" value="註冊" onclick="register();"> 
		<input type="button" value="DEMO" onclick="autofill();"> 
		<input type="button" value="送出" onclick="login();"> 
		<input type="reset" value="清除">
		<input type="button" value="忘記密碼" onclick="goForget();"> 
	</form>
	<div><h3 id="errorMsg" style="margin: 0px auto; width:740px; color: red; font-size:20px; margin-top:5px;\"></h3></div>
	
	</td></tr>
	</table>
	</div>
	<script type="text/javascript">
		function register() {
			$("#main_window").load("account/UserReg.jsp");
		}
		
		function autofill() {
			$("#account").val("kat");
			$("#password").val("pika0101");
		}
		
		function goForget(){
			window.location.href="Forget.jsp?account="+$("#account").val();
		}
		
		function login(){
			$.ajax({
				url: "LoginProcess",
				type: "post",
				data: { 
					account: $('#account').val(), 
					password: $('#password').val()
				},
				success: function (data) {
					var test = data;
					if(test == "false"){
						$("#errorMsg").html("登入失敗，錯誤的使用者名稱或密碼...");
					}else if (test == "activate") {
						console.log("hi");
						$("#main_window").load("account/activate.jsp");
					}
	     	    }	
	  	  });
		}
	</script>
</body>
</html>