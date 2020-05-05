<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<p>這個帳號有註冊，但是還沒啟用</p>
<p>請選下方按鈕重發認證信，若要修改認證信email，請在下方輸入</p>
<form action="" method="post" enctype="multipart/form-data">
	<input type="email" name="email" id="email" value="${email}">
	<input type="button" value="send" onclick="checkNsend();">
</form>
<div id="test"></div>

<script type="text/javascript">
	function checkNsend() {
	
		
		if('${email}' == $("#email").val()){
			$("#test").html("");
			$("#test").append("send mail");
			$("#test").append("!!<br>");
			$("#test").append( '${textUrl}' );
			var textUrl = '${textUrl}';
			var email =  $('#email').val();
			
			$.ajax({
					url: "ResendMail",
					data: {textUrl:  textUrl , email:  email, update : true, uid: '${uid}' },
					type:"GET",
				});
		}else {
			$("#test").html("");
			$("#test").append("update email");
			$("#test").append("<br>");
			$("#test").append("send mail");
			$("#test").append("<br>");
			$("#test").append( '${uid}' );
			$("#test").append("<br>");
			$("#test").append( '${account}' );
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