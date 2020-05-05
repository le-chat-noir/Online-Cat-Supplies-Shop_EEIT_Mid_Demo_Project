<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>使用者註冊</title>
<%@ include file="commonFiles/css/stylesheet.css" %>
<style type="text/css">
	label {
		font-size: 22px;
	}
	
	.note {
		font-size: 15px;
	}
</style>

<script src="commonFiles/js/jquery-3.4.1.min.js"></script>

</head>
<body>
	<%@ include file="commonFiles/Function.jsp"%>
	<%@ include file="commonFiles/Header.jsp"%>
	<%@ include file="commonFiles/Menu.jsp"%>
	<div id="main_window" class="main_window">
		<h1>使用者註冊</h1>
		<hr>
		<form id="regForm" action="CreateAccount" method="post" enctype="multipart/form-data">
			<label>帳號:</label><span class="note">英文&數字的組合，不含特殊位元。<span style="color: red">(必填)</span></span><br>
			<input type="text" id="account" name="account" minlength=3 maxlength=15 required="required" onblur="checkAccountAvalible();"><span id="accMsg"></span><br>
		
			<label>密碼:</label><span class="note">至少8個字，必須同時有英文+數字。<span style="color: red">(必填)</span></span><br>
			<input type="password" id="password" name="password" minlength=8 maxlength=15 required="required" onblur="checkPasswordRule();"><span id="passMsg"></span><br>
		
			<label>確認密碼:</label><br>
			<input type="password" id="verifyPwd" name="verifyPwd" minlength=8 maxlength=15 required="required" onblur="verifyPassword();"><span id="passVerMsg"></span><br>
		
		
			<label>您的大名:</label><span class="note">至少兩個字<span style="color: red">(必填)</span></span><br>
			<input type="text" id="name" name="name" minlength=2 maxlength=10 required="required" onblur="checkName();"><span id="nameMsg"></span><br>
		
			<label>E-mail:</label><span class="note">驗證帳號、寄送電子帳單明細發票等用途<span style="color: red">(必填)</span></span><br>
			<input type="email" id="email" name="email" maxlength=35 length=40 required="required" onblur="checkMail();"><span id="mailMsg"></span><br>
		
			<label>電話:</label><span class="note">填入數字即可，請不要包含括號 ( ) 或分隔號 - <span style="color: red">(必填)</span></span><br>
			<input type="text" id="phone" name="phone" maxlength=15 required="required" onblur="checkPhone();"><span id="phoneMsg"></span><br>
		
			<label>地址:</label><br>
			<input type="text" id="address" name="address" maxlength=50><br> 
		
		
			<label>上傳頭像:</label><br>
		
			<input style="font-size: 20px;" type="file" id="profileImage" name="profileImage" accept="image/jpeg" /><input style="font-size: 20px;" type="button" value="清除" onclick="clearFile();">
			<p>--------------------------------------------------------------------------</p>
			<input style="font-size: 20px; background-color: red; color: white;" type="button" value="DEMO" onclick="fillAll()">
			<input style="font-size: 20px; background-color: green; color: white;" type="button" value="送出" onclick="checkAll()">
			<input style="font-size: 20px;" type="reset" value="清除">
		</form>
	</div>

	<div id="accOK" style="display: none;"></div>
	<script type="text/javascript">
	window.addEventListener( "pageshow", function ( event ) {
		var historyTraversal = event.persisted || ( typeof window.performance != "undefined" && window.performance.navigation.type === 2 );
		if ( historyTraversal ) {
			window.location.reload();
		}
	});	
	
	function checkAccountAvalible() {
		var letters = /^[0-9a-zA-Z]+$/;
		var account = $("[name='account']").val();
		console.log(account);
		if( account.trim().length == 0 ){
			$("#accMsg").html("Cannot empty").css("color", "red");
			return false;
		}else if (account.trim().length < 2) {
			$("#accMsg").html("Account too short").css("color", "red");
			return false;
		}else if ( !account.match(letters)) {
			$("#accMsg").html('只能輸入 "英文" 或 "數字"，不能有空格').css("color", "red");
			return false;
		}else {
			$.ajax({
				type: "post",
				url: "CheckAccountAvailability",
				data: {
					account: account.trim()
				},
				success: function (data) {
					var isAccOK = eval(data);
					if(isAccOK){
						$("#accMsg").html("帳號可用").css("color", "green");
						$("#accOK").html("y");
					}else {
						$("#accMsg").html("帳號已被使用").css("color", "red");
						$("#accOK").html("n");
					}
				}
			})
		}
	}
	
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

	function checkName() {
		var name = $("[name='name']").val().trim();
		if(name.length<2){
			$("#nameMsg").html("至少兩個字").css("color", "red");
			return false;
		}else {
			$("#nameMsg").html("OK").css("color", "green")
			return true;
		}
	}

	function checkMail(){
		var mail = $("[name='email']").val().trim();
		var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
		if( re.test(String(mail).toLowerCase()) ){
			$("#mailMsg").html("OK").css("color", "green")
			return true;
		}else {
			$("#mailMsg").html("無效的 email").css("color", "red");
			return false;
		}
	}
	
	function checkPhone(){
		var phone = $("[name='phone']").val().trim();
		if( /^[0-9]*$/.test(phone) && phone.indexOf(0)==0 && phone.length > 7){
			$("#phoneMsg").html("OK").css("color", "green")
			return true;
		}else {
			$("#phoneMsg").html("格式有錯").css("color", "red");
			return false;
		}
	}
	
	function checkAll() {
		checkAccountAvalible();
		setTimeout(() => {
			if ( verifyPassword() && verifyPassword() && checkName() && checkMail() && checkPhone() && $("#accMsg").html()=="帳號可用" ){
				$("#regForm").submit();
			}else {
				return false;
			}
		}, 200);
	}
	
	function fillAll() {
		$("#account").val("Kat");
		$("#password").val("pika0101");
		$("#verifyPwd").val("pika0101");
		$("#name").val("Kat");
		$("#email").val("no-email@no.email.com");
		$("#phone").val("0900000000");
	}
	
	function clearFile() {
		$("#profileImage").val("");
	}
		
	</script>
	
	
</body>

</html>