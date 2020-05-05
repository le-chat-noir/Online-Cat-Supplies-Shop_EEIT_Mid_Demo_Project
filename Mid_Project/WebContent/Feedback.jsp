<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>意見回饋</title>
	<%@ include file="commonFiles/css/stylesheet.css" %>
	<style type="text/css">
.main_window {
background-image: url("commonFiles/images/Ogawa-Neko-0.png");
}
</style>
</head>
<body>
	<%@ include file="commonFiles/Function.jsp"%>
	<%@ include file="commonFiles/Header.jsp"%>
	<%@ include file="commonFiles/Menu.jsp"%>
	<div id="main_window" class="main_window">
		<p>如果有任何問題或建議，請填寫下方資料並送出</p>
		<form id="feedbackForm" action="ProcessOpinion" method="post">
			Your Name: (必須)<br>
			<input id="name" type="text" value="${sessionScope.name}" required="required"><br>
			Your Email:<br>
			<input id="mail" type="email" value="${sessionScope.email}"><br>
			Feedback Title:<br>
			<input id="title" type="text"><br>
			Your Feedback: (至少15字)<br>
			<textarea id="feedback" rows="5" cols="20" required="required" minlength="20"></textarea><br>
			<input type="button" value="DEMO" style="background-color: green; color: white;" onclick="demo();"><input type="button" value="送出" onclick="sendFeedback();"><input type="reset" value="清除重填">
		</form>
	</div>
		
	<script type="text/javascript">
	window.addEventListener( "pageshow", function ( event ) {
		var historyTraversal = event.persisted || ( typeof window.performance != "undefined" && window.performance.navigation.type === 2 );
		if ( historyTraversal ) {
		    window.location.reload();
		}
	});
	
	function demo() {
		$("#feedback").val("Hello, World~\nHello, MEOW!!!")
	}
	
		function sendFeedback() {
			if($('#feedback').val().length>19){
				$.ajax({
					url: "ProcessOpinion",
					type: "POST",
					data: { 
						name: $('#name').val(), 
						mail: $('#mail').val(), 
						title: $('#title').val(), 
						feedback: $('#feedback').val(),
					},
					success: function(data) {
			        	$("#main_window").load("getFeedback/FeedbackRecieved.jsp");
			        }
			    });
			}
		}
	</script>
	
</body>
</html>