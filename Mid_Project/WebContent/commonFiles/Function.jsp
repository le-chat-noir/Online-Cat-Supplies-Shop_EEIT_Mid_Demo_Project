<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	if(session.getAttribute("name")==null){
		request.setAttribute("showUserName", "登入");
	}else{
		request.setAttribute("showUserName", session.getAttribute("name"));
	}
%>
<style type="text/css">
	#funcBarDiv{
		width: 1000px;
		height: 50px;
		float: clear;
		position: relative;
		margin-top: 50px;
	}
	#funcBar {
		
		float: right;
	}
	#funcBar td{
		width: 100px;
		border: 2px solid black;
	}
	#funcBar td:hover{
		background-color: pink;
	}
	
</style>
</head>
<body>
<div id="funcBarDiv">
<table id="funcBar">
	<tr>
	<%
		if(session.getAttribute("name")==null){
			out.write("<td id='register' style='cursor: pointer;' onclick='register();'>註冊</td>");
		}
	 %>
		<td id="userinfo" style="cursor: pointer;" onclick="goUserSetting();">${showUserName}</td>
		<td id="cart" style="cursor: pointer;" onclick="doCart();">購物車</td>
	<%
		if(session.getAttribute("name")!=null){
			out.write("<td id='orders' style='cursor: pointer;' onclick='orders();'>檢視訂單</td>");
		}
	 %>
		<td id="help" style="cursor: pointer;" onclick="getHelp();">聯絡我們</td>
	<%
		if(session.getAttribute("name")!=null){
			out.write("<td id='logout' style='cursor: pointer;' onclick='logout();'>登出</td>");
		}
	 %>
	</tr>
</table>
</div>
<script type="text/javascript">
	function register() {
		window.location.href="UserReg.jsp";
	}

	function goUserSetting() {
		console.log("Hi");
		console.log( '${sessionScope.name}' );
		if( '${sessionScope.name}' == ""){
			window.location.href="Login.jsp";
		}else{
			window.location.href="UserInfo.jsp";
		}
	}
	
	function doCart() {
		window.location.href="Cart.jsp";
	}
	
	function orders() {
		window.location.href="Orders.jsp";
	}
	
	function getHelp() {
		window.location.href="Feedback.jsp";
	}
	
	function logout() {
		$.confirm({
		    title: '<strong style="font-size: 20px;">確認登出?</strong>',
		    icon: 'fa fa-warning',
		    content: '確定登出?',
		    type: 'red',
		    typeAnimated: true,
		    columnClass: 'col-md-6 col-md-offset-3',
		    buttons: {
		    	取消: function () {},
				登出:{
					btnClass: 'btn-red',
					action: function() {
		       			window.location.href = "logoutServlet.do";
					}
		        },
		    }
		})
	}
	
	
</script>
</body>
</html>