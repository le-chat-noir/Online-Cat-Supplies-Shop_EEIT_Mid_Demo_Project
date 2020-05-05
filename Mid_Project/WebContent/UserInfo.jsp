<%@ page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User Info</title>
		<%@ include file="commonFiles/css/stylesheet.css" %>
	</head>
	<body onload="getUserData();">
		<%@ include file="commonFiles/Function.jsp"%>
		<%@ include file="commonFiles/Header.jsp"%>
		<%@ include file="commonFiles/Menu.jsp"%>
		<div id="main_window" class="main_window">
			<div id="jsp_content">
			${sessionScope.myImage}
				<h1>User Info</h1>
				<h3></h3>
				<p id="changeInfoMsg"></p>
					密碼: <input id="pwd" name="pwd" type="password">	<input type="button"  value="修改資料" onclick="editData();">	
					<h2 id="errMsg"><br></h2>
					<hr>
					<form id="userData">
					<img id="profileImg" src="" style="height: 300px; width: 300px;"/><br>
					名字: <input id="name" name="name" type="text" readonly="readonly" style="background-color: gray; color: white;"><br>
					Email: <input id="mail" name="mail" type="text" readonly="readonly" style="background-color: gray; color: white;"><br>
					電話: <input id="phone" name="phone" type="text" readonly="readonly" style="background-color: gray; color: white;"><br>
					地址: <input id="address" name="address" type="text" readonly="readonly" style="background-color: gray; color: white;"><br>
					
					<input id="profileFile" name="profileImage" style="visibility: hidden;" type="file" disabled="disabled"  accept="image/jpeg"/><input id="clearFile" style="visibility: hidden;" type="button" value="清除" onclick="clearMyFile();" disabled="disabled"/><br>
					<input id="update" style="visibility: hidden;" type="button" value="更新資料" onclick="doDataUpdate();" disabled="disabled">
					<input id="noChange" style="visibility: hidden ;" type="button" value="放棄更新" onclick="returnOriginal();" disabled="disabled">
					</form>
					
					
			</div>
		</div>
		
		<script type="text/javascript">
		window.addEventListener( "pageshow", function ( event ) {
			var historyTraversal = event.persisted || ( typeof window.performance != "undefined" && window.performance.navigation.type === 2 );
			if ( historyTraversal ) {
			    window.location.reload();
			}
		});
		
		function getUserData() {
			$.ajax({
				url: "getUserInfo",
				success: function (data) {
					if(data!=""){
						var jsonObj = JSON.parse(data);
						$("#name").val(jsonObj[0].name);
						$("#mail").val(jsonObj[0].mail);
						$("#phone").val(jsonObj[0].phone);
						$("#address").val(jsonObj[0].address);
						if(jsonObj[0].image!=null){
						$("#profileImg").attr('src', ("data:image/jpg;base64,"+ jsonObj[0].image));
						}else{
							$("#profileImg").attr('src', ("commonFiles/images/no-img.png"));
						}
					
						$("#changeInfoMsg").html("哈囉，"+ jsonObj[0].name+"。若要修改資料，請在下方輸入密碼確認。")
					}else{
						$("#changeInfoMsg").html("請先登入。")
					}
				}
			})
		}
		
		function editData(){
			$.ajax({
				url: "checkPwd",
				data: {pwd: $("#pwd").val()},
				success: function(data){
					if(eval(data)){
						$("#userData").children("input").removeAttr("readonly");
						$("#userData").children("input").css({"background-color": "white", "color": "black"});
						$("#errMsg").html("認證OK");
						$("#update").removeAttr("disabled").css("visibility", "visible");
						$("#noChange").removeAttr("disabled").css("visibility", "visible");
						$("#profileFile").removeAttr("disabled").css("visibility", "visible");
						$("#clearFile").removeAttr("disabled").css("visibility", "visible");
						$("#imgNote").css("visibility", "visible");
					}else{
						$("#errMsg").html("Error");
					}
				}
			})	
		}
			
		function returnOriginal() {
			$("#userData").children("input").attr("readonly", "readonly");
			$("#userData").children("input").css({"background-color": "gray", "color": "white"});
			$("#update").attr("disabled", "disabled").css("visibility", "hidden");
			$("#noChange").attr("disabled", "disabled").css("visibility", "hidden");
			$("#profileFile").attr("disabled", "disabled").css("visibility", "hidden");
			$("#clearFile").attr("disabled", "disabled").css("visibility", "hidden");
			$("#imgNote").css("visibility", "hidden");
			$("#errMsg").html("<br>");
			$("#pwd").val("");
			getUserData();
		}
		
		function doDataUpdate(){
			var formData = new FormData();
			formData.append('name', $("#name").val());
			formData.append('mail', $("#mail").val());
			formData.append('phone', $("#phone").val());
			formData.append('address', $("#address").val());
			formData.append('profileImage', $('#profileFile')[0].files[0]);
			$.ajax({
				
				url: "UpdateUserInfo",
				enctype: "multipart/form-data",
				type: "post",
				contentType: false, //required
			    processData: false, // required
			    mimeType: 'multipart/form-data',
			    data: formData,
				success: function() {
					getUserData();
					returnOriginal();
				}
			})
		}
		
		function clearMyFile() {
			
			$("#profileFile").val("");
		}
		</script>
	</body>
</html>