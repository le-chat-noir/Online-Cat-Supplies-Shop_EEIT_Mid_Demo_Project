<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>重寄認證信</title>
<%@ include file="commonFiles/css/stylesheet.css" %>
</head>
<body>
<%@ include file="commonFiles/Function.jsp"%>
		<%@ include file="commonFiles/Header.jsp"%>
		<%@ include file="commonFiles/Menu.jsp"%>
		<div id="main_window" class="main_window">
			<p>這個帳號有註冊，但是還沒啟用</p>
			<p>請選下方按鈕重發認證信，若要修改認證信email，請在下方輸入</p>
			<form action="" method="post" enctype="multipart/form-data">
			<input type="email" name="email" id="email" value="${email}">
			<input type="button" value="send" onclick="checkNsend();">
			<input type="button" value="一鍵修改" onclick="changeMail();">
			</form>
		<div id="test"></div>

<script type="text/javascript">
	function changeMail() {
		$("#email").val("tamamonogitsune@gmail.com");
	}


	function checkNsend() {
	
		
		if('${email}' == $("#email").val()){
			
			var textUrl = '${textUrl}';
			var email =  $('#email').val();
			
			$.ajax({
					url: "ResendMail",
					data: {textUrl:  textUrl , email:  email, update : true, uid: '${uid}' },
					type:"GET",
				});
		}else {
			
			$.ajax({
				url: "ResendMail",
				data: {textUrl:  '${textUrl}' , email:  $('#email').val(), update : true , uid: '${uid}'},
				type:"GET",
			});
		}
		
		
	
		
	}
		
	



</script>

</body>
</html>